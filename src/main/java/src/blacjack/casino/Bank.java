package src.blacjack.casino;

import src.blacjack.bet.Bet;
import src.blacjack.players.Player;

import java.util.concurrent.CopyOnWriteArrayList;

public class Bank {

    private int totalAmount;

    private CopyOnWriteArrayList<Player> playersInBankList;

    public Bank() {
        this.playersInBankList = new CopyOnWriteArrayList<>();
    }

    public void addPlayerBet(Player p) {
        playersInBankList.add(p);
    }

    public CopyOnWriteArrayList<Player> getPlayersInBankList() {
        return playersInBankList;
    }

    public void clearplayersInBankList() {
        playersInBankList.clear();
    }

    public void payWinners(Player p, Bet bet) {
        double sumToPayToWinner = 0;
        sumToPayToWinner += bet.getSum() * bet.getOdd();
        System.out.println("Player " + p.getName() + " won "
                + sumToPayToWinner + " with bet" + bet.toString());
        p.addMoney(sumToPayToWinner);
        p.setLastWin(true);
        totalAmount -= sumToPayToWinner;
    }


    public void withdrawLoses(Player p, Bet bet) {
        double sumToSubstractFromLoser = bet.getSum();
        System.out.println("Player " + p.getName() + " lose " +
                sumToSubstractFromLoser + " with bet" + bet.toString());
        p.transferMoney(sumToSubstractFromLoser);
        p.setLastWin(false);
        totalAmount += sumToSubstractFromLoser;

    }

    public void dealWithHouse(Home house) {
        System.out.println("\nBank TotalAmount after round " + totalAmount);
        house.addHomeMoney(totalAmount);
        totalAmount = 0;
    }

}
