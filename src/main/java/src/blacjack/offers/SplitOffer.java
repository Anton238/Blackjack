package src.blacjack.offers;

import src.blacjack.bet.Bet;
import src.blacjack.casino.cards.Card;
import src.blacjack.game.BlackJackGame;
import src.blacjack.players.AbstactPlayer;
import src.blacjack.players.Hand;
import src.blacjack.players.Player;
import java.util.List;

public class SplitOffer extends Offer {

    @Override
    public boolean acceptOffer(AbstactPlayer p) {
        return (int) (Math.random() * 2) == 1;
    }

    @Override
    public void performOffer(BlackJackGame game, Player p) {
        if (acceptOffer(p)) {
            System.out.println(p.getName() + " Player accept offer Split");
            for (Hand hand : p.getHands()) {
                if (hand.isSplit()) {
                    p.setSplittedPlayer(true);
                    proposeOffersForSplit(game, p);
                }
            }
        }
    }

    private void proposeOffersForSplit(BlackJackGame game, Player p) {
        collectBetForSecondHand(p, game);
        addCardsToSplittedHandAndDealer(p, game);
        game.casePlayerBlackJack(p);
        game.casePlayerBlackJackAndDealerAce(p);
        game.caseInsurance(p);
        game.caseDouble(p);
        game.caseSplit(p);
        game.caseHit(p);
        for(Hand hand : p.getHands()){
            hand.setOfferType(OffersTypes.Split);
        }
    }

    private void collectBetForSecondHand (Player p, BlackJackGame game){
        List<Bet> bets = p.getNextBets(5, 1000, game);
        if (bets == null) return;
        if (p.getHands().get(1) != null )
            p.getHands().get(1).setBets(bets);
    }

    private void addCardsToSplittedHandAndDealer(Player p, BlackJackGame game){
        Card cardForNewHand = p.getFirstHand().getHandCards().get(1);
        p.getFirstHand().getHandCards().remove(cardForNewHand);
        p.getSecondHand().addCardToHand(cardForNewHand);
        for(Hand hand : p.getHands()){
            while(hand.isHit()){
                hand.addCardToHand(game.getCardManager().getFirstCardFromCards());
            }
        }
        game.getDealer().getFirstHand().addCardToHand(game.getCardManager().getFirstCardFromCards());

    }
}
