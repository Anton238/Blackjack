package src.blacjack.bet;

import src.blacjack.players.Hand;

public abstract class Bet {
    double sum;
    double odd;
    public abstract int winOrLoss(Hand dealerHand, Hand playerhand);
    public void setSum(double sum) {
        this.sum = sum;
    }
    public double getSum(){ return sum; }
    public double getOdd(){
        return odd;
    }
    public void setOdd(double odd){
        this.odd = odd;
    };
}
