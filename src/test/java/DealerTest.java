import src.blacjack.casino.cards.CardManager;
import src.blacjack.players.Dealer;
import src.blacjack.players.Player;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class DealerTest {
    private Dealer d = new Dealer();
    private CardManager manager = new CardManager();
    private Player p = new Player();
    private List<Player> list = new ArrayList<>();

    @Test
    public void addCardsToDealerHand(){
        Assert.assertTrue(d.getFirstHand().getHandCards().size()  == 0 );
        d.addCardsToDealerhand(manager);
        Assert.assertTrue(d.getFirstHand().getHandCards().size() >= 2);
        System.out.println(d.getFirstHand().toString());

    }

    @Test
    public void giveinitcards(){
        Assert.assertTrue(d.getFirstHand().getHandCards().size()  == 0 );
        list.add(p);
        d.giveInitialCards(manager, list);
        System.out.println(p.getFirstHand().toString());
        Assert.assertTrue(d.getFirstHand().getHandCards().size() >= 2);
    }
}
