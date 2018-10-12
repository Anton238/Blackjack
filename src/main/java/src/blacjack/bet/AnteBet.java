package src.blacjack.bet;

import src.blacjack.players.Hand;

public class AnteBet extends Bet {

    public AnteBet(double sum) {
        this.odd = 1;
        this.sum = sum;
    }

    @Override
    public int winOrLoss(Hand dealerHand, Hand playerHand) {
        if (playerHand.isBurst()) return -1;
        if (dealerHand.isBurst()) return 1;
        if (playerHand.getSumOfCards() > dealerHand.getSumOfCards()) return 1;
        if (playerHand.getSumOfCards() < dealerHand.getSumOfCards()) return -1;
        return 0;
    }

    @Override
    public String toString() {
        return "AnteBet{" +
                ", sum = " + sum +
                ", odd = " + odd +
                '}';
    }
}
