package src.blacjack.players;

public class PlayerNameGenerator {
    private static int playerNameGenerator;
    static String generateName(){
        return "player" + (++playerNameGenerator);
    }
}
