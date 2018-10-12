import org.junit.Assert;
import org.junit.Test;
import src.blacjack.casino.cards.Card;
import src.blacjack.casino.cards.CardManager;
import java.util.List;
import java.util.stream.Collectors;


public class CardInitTest {
    @Test
    public void initCards(){
        CardManager manager = new CardManager();
        List<Card> sortedByValueCards = manager.getCards().stream()
                .sorted((e1, e2) -> (e1.getCardValue() - e2.getCardValue())).
                        collect(Collectors.toList());
        Assert.assertTrue(manager.getCards().size() == 52);
        Assert.assertTrue(sortedByValueCards.get(51).getCardValue() == 11);
        Assert.assertTrue(sortedByValueCards.get(0).getCardValue() == 2);
    }
}
