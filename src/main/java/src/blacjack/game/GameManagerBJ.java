package src.blacjack.game;

import src.blacjack.casino.Home;
import src.blacjack.players.Player;

import java.util.Scanner;

public class GameManagerBJ {

    public Home house;
    public BlackJackGame game;

    public GameManagerBJ() {
        this.house = new Home(5, 1000);
        this.game = new BlackJackGame();
    }

    public static void main(String[] args) {
        GameManagerBJ gameManager = new GameManagerBJ();
        gameManager.createPlayersForBlackJack();
        System.out.println(gameManager.game.getPlayers().toString());
        gameManager.game.start(gameManager.house);
    }

    private void createPlayersForBlackJack() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of auto players: ");
        int auto = scanner.nextInt();
        if (auto > 0) {
            System.out.print("Enter initial amount of money for Players: ");
            int initMoneyForPlayer = scanner.nextInt();
            for (int i = 0; i < auto; i++) {
                Player p = new Player(initMoneyForPlayer, house.getMinBet());
                game.addPlayer(p);
            }
        }
    }
}

