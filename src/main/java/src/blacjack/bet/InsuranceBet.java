package src.blacjack.bet;

import src.blacjack.players.Hand;

public class InsuranceBet extends Bet {

    public InsuranceBet(double anteBet) {
        this.setSum(anteBet/2);
        this.odd = 2;
    }

    @Override
    public int winOrLoss(Hand dealerHand, Hand playerhand) {
        if (dealerHand.isBlackJack()) return 1;
        else return -1;
    }

    @Override
    public String toString() {
        return "InsuranceBet{" +
                "sum = " + sum +
                ", odd = " + odd +
                '}';
    }
}
