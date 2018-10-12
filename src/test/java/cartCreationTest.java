import src.blacjack.casino.cards.Card;
import src.blacjack.casino.cards.CardManager;
import org.junit.Assert;
import org.junit.Test;

public class cartCreationTest {

    @Test
    public void testShuffleCards() {
        CardManager cardManager = new CardManager();
        Card c1 = cardManager.getCards().get(0);
        cardManager.shuffleCards();
        Card c2 = cardManager.getCards().get(0);
        Assert.assertTrue(!(c1.equals(c2)));
    }
}
