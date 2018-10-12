package src.blacjack.offers;

import src.blacjack.bet.InsuranceBet;
import src.blacjack.game.BlackJackGame;
import src.blacjack.players.AbstactPlayer;
import src.blacjack.players.Hand;
import src.blacjack.players.Player;

public class Insurance extends Offer {

    @Override
    public boolean acceptOffer(AbstactPlayer p) {
        return (int) (Math.random() * 2) == 1;
    }

    @Override
    public void performOffer(BlackJackGame game, Player p) {
        if (acceptOffer(p)) {
            System.out.println(p.getName() + " accept offer Insurance");
            for (Hand hand : p.getHands()) {
                if (hand.getBets().size() > 0 && hand.getOfferType() != OffersTypes.EvenMoney) {
                    double betSum = hand.getBets().get(0).getSum();
                    hand.getBets().add(new InsuranceBet(betSum));
                }
            }
        } else System.out.println(p.getName() + " does not accept offer Insurance");
    }

}
