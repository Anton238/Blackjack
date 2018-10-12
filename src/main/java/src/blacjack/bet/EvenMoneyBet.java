package src.blacjack.bet;

import src.blacjack.players.Hand;

public class EvenMoneyBet extends Bet {

    public EvenMoneyBet(double sum) {
        super();
        this.odd = 1;
        this.sum = sum;
    }

    @Override
    public int winOrLoss(Hand dealerHand, Hand playerHand) {
        return 0;
    }

    @Override
    public String toString() {
        return "EvenMoneyBet{" +
                "sum = " + sum +
                '}';
    }
}
