package src.blacjack.casino;

public class Home {

    private int maxBet;
    private int minBet;
    private int homeMoney;


    public Home(int minBet, int maxBet) {
        this.maxBet = maxBet;
        this.minBet = minBet;
    }

    public int getMaxBet() {
        return maxBet;
    }

    public int getMinBet() {
        return minBet;
    }

    public int getHomeMoney() {
        return homeMoney;
    }

    void addHomeMoney(int add) {
        this.homeMoney += add;
    }
}


