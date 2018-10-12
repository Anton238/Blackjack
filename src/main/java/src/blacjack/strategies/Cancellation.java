package src.blacjack.strategies;

import src.blacjack.bet.Bet;
import src.blacjack.players.Player;
import java.util.Arrays;
import java.util.LinkedList;

public class Cancellation extends Strategy {

    private final LinkedList<Integer> baseNums;
    private LinkedList<Integer> currentNums;

    public Cancellation(int baseBet) {
        super(baseBet);
        baseNums = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        this.currentNums = new LinkedList<>(baseNums);
    }

    @Override
    public Bet nextBet(Player p) {
        Bet nextBet = getNextAnteBet();
        nextBet.setSum(nextBetSum(p));
        return nextBet;
    }


    @Override
    public Bet getNextAnteBet() {
        // random type
        return super.getNextAnteBet();
    }

    @Override
    public int nextBetSum(Player p) {
        if(currentNums.size() < 2){
            currentNums = baseNums;
        }
            if (p.isLastWin()) {
                currentNums.removeFirst();
                currentNums.removeLast();
            } else {
                if (!p.isLastWin())
                    currentNums.add(currentNums.getFirst() + currentNums.getLast());
            }

        if(currentNums.size() <= 2){
            currentNums = baseNums;
        }
        return currentNums.getFirst() + currentNums.getLast();
    }

    @Override
    public String toString() {
        return "Cancellation{" +
                '}';
    }
}
