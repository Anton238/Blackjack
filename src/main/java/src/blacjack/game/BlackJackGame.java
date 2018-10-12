package src.blacjack.game;

import src.blacjack.bet.Bet;
import src.blacjack.casino.Bank;
import src.blacjack.casino.Home;
import src.blacjack.casino.cards.AceCard;
import src.blacjack.casino.cards.Card;
import src.blacjack.casino.cards.CardManager;
import src.blacjack.offers.*;
import src.blacjack.players.Dealer;
import src.blacjack.players.Hand;
import src.blacjack.players.Player;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BlackJackGame extends Game {
    private List<Player> players;
    private Bank bank;
    private List<Card> cards;
    private CardManager cardManager;
    private Dealer dealer;

    public BlackJackGame() {
        this.players = new LinkedList<>();
        this.bank = new Bank();
        this.cardManager = new CardManager();
        this.cards = cardManager.getCards();
        this.dealer = new Dealer();
    }

    public BlackJackGame(List<Player> players) {
        this.players = new LinkedList<>();
        this.bank = new Bank();
        this.cardManager = new CardManager();
        this.cards = cardManager.getCards();
        this.dealer = new Dealer();

    }

    public Dealer getDealer() {
        return dealer;
    }

    public CardManager getCardManager() {
        return cardManager;
    }

    public List<Player> getPlayers() {
        return players;
    }

    void addPlayer(Player player) {
        players.add(player);
    }

    public Bank getBank() {
        return bank;
    }

    @Override
    public void start(Home house) {
        while (players.size() > 0) {
            collectBetsForAllPlayers(house);
            if (!ifPlayersCanPlay(house)) break;
            cardManager.shuffleCards();
            dealer.giveInitialCards(cardManager, players);
            showPlayersInBank();
            proposeOffer();
            resolveBets(dealer.getFirstHand(), bank.getPlayersInBankList());
            afterPlayCleaningAndPaymentsToHouse(house);
        }
    }

    public void collectBetsForAllPlayers(Home house) {
        int currSize;
        for (int i = 0; i < players.size() && players.size() != 0; i++) {
            currSize = players.size();
            Player p = players.get(i);
            collectBetForFirstHand(p, house.getMinBet(), house.getMaxBet());
            if (currSize != players.size())
                i--;
        }
    }

    private void collectBetForFirstHand(Player p, int minBet, int maxBet) {
        List<Bet> bets = p.getNextBets(minBet, maxBet, this);
        if (bets == null) return;
        if (p.getFirstHand() != null)
            p.getFirstHand().setBets(bets);
        bank.addPlayerBet(p);
    }

    public void removePlayer(Player p) {
        players.remove(p);
    }

    private void proposeOffer() {

        for (Player p : bank.getPlayersInBankList()) {
            casePlayerBlackJack(p);
            casePlayerBlackJackAndDealerAce(p);
            caseInsurance(p);
            caseDouble(p);
            caseSplit(p);
            if (!p.isSplittedPlayer()) {
                caseHit(p);
            }


        }
    }

    public void casePlayerBlackJack(Player p) {
        for (Hand hand : p.getHands()) {
            if (hand != null && hand.getHandCards().size() > 0) {
                if (hand.isBlackJack()) {
                    hand.getBets().get(0).setOdd(1.5);
                    System.out.print("SplitPlayer with od 1.5: " + p);
                }
            }
        }
    }

    public void casePlayerBlackJackAndDealerAce(Player p) {
        if ((dealer.getFirstHand().getHandCards().get(0).getClass() == AceCard.class))
            for (Hand hand : p.getHands())
                if (hand.isBlackJack())
                    new EvenMoney().performOffer(this, p);
    }

    public void caseInsurance(Player p) {
        if (dealer.getFirstHand().getHandCards().get(0).getClass() == AceCard.class)
            new Insurance().performOffer(this, p);
    }

    public void caseDouble(Player p) {
        for (Hand hand : p.getHands())
            if (hand.isDouble())
                new DoubleBetOffer().performOffer(this, p);
    }


    public void caseSplit(Player p) {
        for (Hand hand : p.getHands())
            if (hand.isSplit())
                new SplitOffer().performOffer(this, p);
    }


    public void caseHit(Player p) {
        System.out.println("Casehit " + p.toString());
        new Hit().performOffer(this, p);
    }

    public void resolveBets(Hand dealerHand, CopyOnWriteArrayList<Player> playerList) {
        for (Player p : playerList) {
            if (p.isAllPlayersHandsAreEmpty()) continue;
            System.out.println("\nDealer has " + dealerHand.getSumOfCards()
                    + "\nPlayer " + p.getName() + " has " + p.getFirstHand().getSumOfCards());
            if (p.getSecondHand().getHandCards().size() > 0)
                System.out.println("Player " + p.getName() + " has " + p.getSecondHand().getSumOfCards());
            for (Hand hand : p.getHands())
                if (hand != null && hand.getHandCards().size() > 0)
                    resolveBetsForHand(p, hand, dealerHand);

            determineSummarizedLosWinForPlayer(p);
        }
    }

    private void determineSummarizedLosWinForPlayer(Player p) {
        if (p.getCurrentGameWinLossAmount() > 0)
            p.setLastWin(true);
        else p.setLastWin(false);

        System.out.println("Player " + p.getName() + ": total bet result is " + p.getCurrentGameWinLossAmount());
    }

    private void resolveBetsForHand(Player p, Hand hand, Hand dealerHand) {
        if (hand.getBets().size() > 0) {
            for (Bet bet : hand.getBets()) {
                if (bet != null) {
                    if (bet.winOrLoss(dealerHand, hand) == 1)
                        bank.payWinners(p, bet);
                    else if (bet.winOrLoss(dealerHand, hand) == -1)
                        bank.withdrawLoses(p, bet);
                }
            }
        }
    }

    private void showPlayersInBank() {
        System.out.println("\nBank has:");
        for (Player p : bank.getPlayersInBankList()) {
            System.out.println("\n" + p.getName() + ":");
            for (Hand hand : p.getHands()) {
                System.out.println(hand.toString());
            }
        }
    }

    private void afterPlayCleaningAndPaymentsToHouse(Home house) {
        bank.clearplayersInBankList();
        dealer.clearHands();
        System.out.println("\nPlayers after cycle:");
        for (Player p : players) {
            p.clearHands();
            p.setCurrentGameWinLossAmount(0);
            System.out.println(p);
        }

        bank.dealWithHouse(house);
        System.out.println("MONEY IN HOUSE = " + house.getHomeMoney());
    }

    private boolean ifPlayersCanPlay(Home house) {
        if (players.size() == 0) {
            bank.dealWithHouse(house);
            System.out.println("Game over");
            return false;
        }
        return true;
    }
}

