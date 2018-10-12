import src.blacjack.casino.cards.AceCard;
import src.blacjack.casino.cards.Card;
import src.blacjack.casino.cards.CardColorType;
import src.blacjack.casino.cards.PointCards;
import src.blacjack.players.Hand;
import org.junit.Assert;
import org.junit.Test;

public class HandTest {

    private Card card10C = new PointCards(10,CardColorType.CLUB);
    private Card card10D = new PointCards(10,CardColorType.DIAMOND);
    private Card cardAceC = new AceCard(CardColorType.CLUB);
    private Card cardAceD = new AceCard(CardColorType.DIAMOND);

    @Test
    public void calculateSum() {
        Hand hand =  new Hand();
        hand.addCardToHand(card10C);
        Assert.assertTrue(hand.getSumOfCards() == 10);
        hand.addCardToHand(cardAceC);
        Assert.assertTrue(hand.getSumOfCards() == 21);
        hand.addCardToHand(cardAceD);
        System.out.println(hand.getSumOfCards() );
        Assert.assertTrue(hand.getSumOfCards() == 22);
    }

    @Test
    public void checkHandContainsAce() {
        Hand hand =  new Hand();
        hand.addCardToHand(card10C);
        hand.addCardToHand(cardAceC);
        Assert.assertTrue(hand.handContainsAce());
    }

    @Test
    public void checkHandContainsNumber() {
        Hand hand =  new Hand();
        hand.addCardToHand(card10C);
        hand.addCardToHand(cardAceC);
        Assert.assertTrue(hand.handContainsNumber(10));
    }

    @Test
    public void checkBlackJack() {
        Hand hand =  new Hand();
        hand.addCardToHand(card10C);
        hand.addCardToHand(cardAceC);
        Assert.assertTrue(hand.handContainsAce());
        Assert.assertTrue(hand.handContainsNumber(10));
        Assert.assertTrue(hand.handContainsAce() && hand.handContainsNumber(10) );
        Assert.assertTrue(hand.isBlackJack());
    }

    @Test
    public void checkIsBurst() {
        Hand hand =  new Hand();
        hand.addCardToHand(card10C);
        hand.addCardToHand(cardAceC);
        hand.addCardToHand(card10D);
        Assert.assertTrue(hand.isBurst());
    }

    @Test
    public void checkIsSplit() {
        Hand hand =  new Hand();
        hand.addCardToHand(card10C);
        hand.addCardToHand(card10D);
        Assert.assertTrue(hand.isSplit());
    }

    @Test
    public void checkIsDouble() {
        Hand hand =  new Hand();
        hand.addCardToHand(card10C);
        hand.addCardToHand(cardAceC);
        Assert.assertTrue(hand.isDouble());
    }

    @Test
    public void checkIsHit() {
        Hand hand =  new Hand();
        hand.addCardToHand(card10C);
        Assert.assertTrue(hand.isHit());
    }

}
