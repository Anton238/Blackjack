package src.blacjack.strategies;

import src.blacjack.bet.AnteBet;
import src.blacjack.bet.Bet;
import src.blacjack.players.Player;

public abstract class Strategy {
    private int baseBet;

    public abstract Bet nextBet(Player p);

    public abstract int nextBetSum(Player p);

    Strategy(int baseBet) {
        this.baseBet = baseBet;
    }

    public Bet getNextAnteBet(){
        return  new AnteBet(baseBet);
    }

    public int getBaseBet(){
        return baseBet;
    }
}
