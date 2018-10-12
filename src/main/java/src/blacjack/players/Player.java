package src.blacjack.players;

import src.blacjack.bet.Bet;
import src.blacjack.game.BlackJackGame;
import src.blacjack.strategies.Cancellation;
import src.blacjack.strategies.Martingale;
import src.blacjack.strategies.Strategy;
import src.blacjack.strategies.StrategyType;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player extends AbstactPlayer{
    private double playerMoney;
    private String name;
    private Strategy strategy;
    private boolean isLastWin;
    private double currentGameWinLossAmount;

    public double getCurrentGameWinLossAmount() {
        return currentGameWinLossAmount;
    }

    public void setCurrentGameWinLossAmount(double currentGameWinLossAmount) {
        this.currentGameWinLossAmount = currentGameWinLossAmount;
    }

    public boolean isLastWin() {
        return isLastWin;
    }

    public void setLastWin(boolean lastWin) {
        isLastWin = lastWin;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public Player() {
        splittedPlayer = false;
    }

    public Player(double playerMoney, String name, Strategy strategy, boolean isLastWin) {
        this.playerMoney = playerMoney;
        this.name = name;
        this.strategy = strategy;
        this.isLastWin = isLastWin;
        splittedPlayer = false;
        currentGameWinLossAmount = 0;
    }

    public Player(int initialMoney, int baseBet) {
        this.name = PlayerNameGenerator.generateName();
        this.playerMoney = initialMoney;
        this.strategy = getRandomStrategy(baseBet);
        this.isLastWin = true;
        splittedPlayer = false;
        currentGameWinLossAmount = 0;
    }

    public Strategy getRandomStrategy(int baseBet) {
        int index = new Random().nextInt(StrategyType.values().length);
        StrategyType strategyType = StrategyType.values()[index];
        switch (strategyType) {
            case MARTINGALE:
                return new Martingale(baseBet);
            case CANCELLATION:
                return new Cancellation(baseBet);
        }
        return null;
    }

    private void resetStrategy() {
        setStrategy(getRandomStrategy(strategy.getBaseBet()));
    }

    public void transferMoney(double substract) {
        currentGameWinLossAmount -= substract;
        playerMoney = playerMoney - substract;
    }

    public void addMoney(double add) {
        currentGameWinLossAmount += add;
        playerMoney = playerMoney + add;
    }

    public List<Bet> getNextBets(int minBet, int maxBet, BlackJackGame game) {
        List<Bet> betsList = new ArrayList<>();
        Bet anteBet = getNextAnteBet(minBet, maxBet, game);
        betsList.add(anteBet);
        return betsList;
    }


    public Bet getNextAnteBet(int minBet, int maxBet, BlackJackGame game) {

        if (playerMoney < minBet) {
            System.out.println(" PLAYER " + this + "HAVE NOT ENOUGH MONEY");
            game.removePlayer(this);
            return null;
        }

        Bet bet = strategy.nextBet(this);

        if (bet != null) {
            if (playerMoney == minBet) {
                bet.setSum(minBet);
                return bet;
            }
            if (bet.getSum() < minBet || bet.getSum() > maxBet) {
                resetStrategy();
                bet = getNextAnteBet(minBet, maxBet, game);
            }
            if (bet.getSum()* bet.getOdd() > playerMoney && bet.getSum() < minBet) {
                resetStrategy();
                bet = getNextAnteBet(minBet, maxBet, game);
            }
        }
        return bet;
    }


    @Override
    public String toString() {
        return "Player{" +
                "hand=" + hands +
                ", playerMoney=" + playerMoney +
                ", name='" + name + '\'' +
                ", strategy=" + strategy +
                ", isLastWin=" + isLastWin +
                '}';
    }

    public String getName(){
        return this.name;
    }
}
