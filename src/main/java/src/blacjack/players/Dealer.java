package src.blacjack.players;

import src.blacjack.casino.cards.Card;
import src.blacjack.casino.cards.CardManager;
import java.util.List;

public class Dealer extends AbstactPlayer {

    public Dealer() {
        super();
        this.name = "Dealer";
    }

    public void giveInitialCards(CardManager manager, List<Player> players) {
        manager.shuffleCards();

        for (Player p : players) {
            giveNumberOfCardsToPlayersHand(2, p.getFirstHand(), manager);
        }

        // add for dealer
        giveNumberOfCardsToPlayersHand(2, getFirstHand(), manager);
        System.out.println("\nDealer has " + getFirstHand().toString());
    }

    @Override
    public String toString() {
        return "Dealer{" +
                "hand = " + hands +
                '}';
    }

    private void giveNumberOfCardsToPlayersHand(int numberOfCards, Hand hand, CardManager manager) {
        int i = 0;
        while (i++ < numberOfCards) {
            hand.addCardToHand(manager.getFirstCardFromCards());
        }
    }


    public void addCardsToDealerhand(CardManager manager) {
        while (getFirstHand().isHit()) {
            Card card = manager.getFirstCardFromCards();
            System.out.println(" Dealer added " + card.toString());
            addCardToFirstHand(card);
        }
    }
}
