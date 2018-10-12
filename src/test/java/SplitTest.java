import src.blacjack.casino.Home;
import src.blacjack.casino.cards.CardColorType;
import src.blacjack.casino.cards.PointCards;
import src.blacjack.game.BlackJackGame;
import src.blacjack.players.Dealer;
import src.blacjack.players.Player;
import src.blacjack.strategies.Martingale;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class SplitTest {
    @Test
    public void splitTest() {
        BlackJackGame game = new BlackJackGame();

        Player p = new Player(50, 5);
        p.setStrategy(new Martingale(5));
        List<Player> list = new ArrayList<>();
        list.add(p);
        game.getPlayers().addAll(list);
        game.collectBetsForAllPlayers(new Home(5, 1000));

        Dealer d = game.getDealer();
        d.getFirstHand().addCardToHand(new PointCards(5, CardColorType.CLUB));
        d.getFirstHand().addCardToHand(new PointCards(10, CardColorType.CLUB));

        p.getFirstHand().addCardToHand(new PointCards(6, CardColorType.CLUB));
        p.getFirstHand().addCardToHand(new PointCards(6, CardColorType.CLUB));

        game.casePlayerBlackJack(p);
        game.casePlayerBlackJackAndDealerAce(p);
        game.caseInsurance(p);
        game.caseSplit(p);
        game.caseHit(p);
        game.resolveBets(d.getFirstHand(), game.getBank().getPlayersInBankList());

        System.out.println(p.toString());
    }
}
