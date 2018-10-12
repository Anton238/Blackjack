package src.blacjack.offers;

import src.blacjack.bet.Bet;
import src.blacjack.bet.EvenMoneyBet;
import src.blacjack.game.BlackJackGame;
import src.blacjack.players.AbstactPlayer;
import src.blacjack.players.Hand;
import src.blacjack.players.Player;

public class EvenMoney extends Offer {

    @Override
    public boolean acceptOffer(AbstactPlayer p) {
        return (int) (Math.random() * 2) == 1;
    }

    @Override
    public void performOffer(BlackJackGame game, Player p) {
        for (Hand hand : p.getHands()) {
            if (acceptOffer(p) && hand.isBlackJack()) {
                performOfferEvenMoneyProcess(game, hand, p);
            } else System.out.println(p.getName() + "Player does not accept evenMoney offer for hand " + hand);
        }
    }

    private void performOfferEvenMoneyProcess(BlackJackGame game, Hand hand, Player p) {
        System.out.println(" accept EvenMoney offer for hand " + hand);
        //immediate pay
        double sum = hand.getBets().get(0).getSum();
        hand.getBets().add(new EvenMoneyBet(sum));
        for (Bet bet : hand.getBets()) {
            if (bet instanceof EvenMoneyBet) {
                game.getBank().payWinners(p, bet);
                p.setLastWin(true);
            }
        }
        hand.setOfferType(OffersTypes.EvenMoney);
        hand.clearHand();
    }
}

