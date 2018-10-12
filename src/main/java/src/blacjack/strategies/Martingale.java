package src.blacjack.strategies;

import src.blacjack.bet.Bet;
import src.blacjack.players.Player;

public class Martingale extends Strategy {
    private int currentBet;
    private int minBet;

    public Martingale(int baseBet) {
        super(baseBet);
        currentBet = baseBet;
        minBet = baseBet;
    }

    @Override
    public Bet nextBet(Player p) {
        Bet nextBet = getNextAnteBet();
        nextBet.setSum(nextBetSum(p));
        return nextBet;
    }

    public int nextBetSum(Player p) {
        if (p.getHands().size() == 0)
            return minBet;
        else {
            if (!p.isLastWin()) {
                currentBet = currentBet * 2;
            } else {
                currentBet = minBet;
            }
        }
        return currentBet;
    }

    public Bet getNextAnteBet() {
        //get random Bet
        return super.getNextAnteBet();
    }

    @Override
    public String toString() {
        return "Martingale{" +
                '}';
    }
}
