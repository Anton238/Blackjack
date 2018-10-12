package src.blacjack.offers;

import src.blacjack.bet.AnteBet;
import src.blacjack.bet.Bet;
import src.blacjack.casino.Bank;
import src.blacjack.casino.cards.Card;
import src.blacjack.game.BlackJackGame;
import src.blacjack.players.AbstactPlayer;
import src.blacjack.players.Hand;
import src.blacjack.players.Player;

public class Hit extends Offer {

    @Override
    public boolean acceptOffer(AbstactPlayer p) {
        return p.isAnyOfHandsHit();
    }

    @Override
    public void performOffer(BlackJackGame game, Player p) {
        addCardsHitOffer(p, game);
        caseBurst(game, p);
        addCardsHitOffer(game.getDealer(), game);
    }

    private void addCardsHitOffer(AbstactPlayer abstactPlayer, BlackJackGame game) {
        if (abstactPlayer.isSplittedPlayer()) {
            for (Hand hand : abstactPlayer.getHands()) {
                addCardToHandIfHit(hand, game, abstactPlayer);
                hand.setOfferType(OffersTypes.Hit);
            }
        } else addCardToHandIfHit(abstactPlayer.getFirstHand(), game, abstactPlayer);

    }

    private void addCardToHandIfHit(Hand hand, BlackJackGame game, AbstactPlayer p) {
        while (hand.isHit()) {
            Card newCard = game.getCardManager().getFirstCardFromCards();
            hand.addCardToHand(newCard);
            System.out.println(" Card" + newCard + " aded to hand  " + p.getName());
        }
    }

    private void caseBurst(BlackJackGame game, Player p) {
        Bank bank = game.getBank();
        for (Hand hand : p.getHands()) {
            if (hand.isBurst()) {
                Bet betToRemove = null;
                for (Bet bet : hand.getBets()) {
                    if (bet instanceof AnteBet) {
                        bank.withdrawLoses(p, bet);
                        betToRemove = bet;
                    }
                }
                hand.getBets().remove(betToRemove);
            }
        }
    }
}
