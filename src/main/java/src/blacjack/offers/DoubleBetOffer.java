package src.blacjack.offers;

import src.blacjack.bet.AnteBet;
import src.blacjack.bet.Bet;
import src.blacjack.casino.cards.Card;
import src.blacjack.game.BlackJackGame;
import src.blacjack.players.AbstactPlayer;
import src.blacjack.players.Hand;
import src.blacjack.players.Player;

public class DoubleBetOffer extends Offer {

    @Override
    public boolean acceptOffer(AbstactPlayer p) {
        return (int) (Math.random() * 2) == 1;
    }

    @Override
    public void performOffer(BlackJackGame game, Player p) {
        for (Hand hand : p.getHands()) {
            if (acceptOffer(p) && hand.isDouble()) {
                hand.setOfferType(OffersTypes.Double);
                performDoubleOfferProcess(game, hand);
            }
        }
    }

    private void performDoubleOfferProcess(BlackJackGame game, Hand hand) {
        System.out.println("\nPlayer accept double offer for hand  " + hand);
        for (Bet bet : hand.getBets()) {
            if (bet instanceof AnteBet) {
                // check money
                bet.setOdd(2);
            }
        }
        // get 1 card and add cards to dealer
        performDoubleForHand(game, hand);
    }

    private void performDoubleForHand(BlackJackGame game, Hand hand) {
        if (hand.isHit()) {
            Card card = game.getCardManager().getFirstCardFromCards();
            System.out.println(hand + "added " + card.toString());
            hand.addCardToHand(card);
        }
        game.getDealer().addCardsToDealerhand(game.getCardManager());
    }

}
