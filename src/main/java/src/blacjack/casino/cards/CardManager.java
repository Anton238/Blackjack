package src.blacjack.casino.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardManager {

    private List<Card> cards = new ArrayList<>();
    private List<Card> removedCards = new ArrayList<>();

    public CardManager() {
        initCards();
        shuffleCards();
    }

    private void initCards() {
        for (CardColorType type : CardColorType.values()) {
            createCardColorRank(type);
        }
    }

    public List<Card> getCards() {
        return cards;
    }

    private void addToRemovedCards(Card card) {
        removedCards.add(card);
    }

    private void createCardColorRank(CardColorType cardColorType) {
        cards.add(new AceCard(cardColorType));
        for (int i = 2; i < 11; i++) {
            cards.add(new PointCards(i, cardColorType));
        }
        for (CardPictureType type : CardPictureType.values()) {
            cards.add(new FacesCards(type, cardColorType));
        }
    }

    public void shuffleCards() {
        Collections.shuffle(cards);
    }

    private boolean isEnoughCards() {
        return cards.size() > 1;
    }

    public Card getFirstCardFromCards() {
        if (!isEnoughCards()) {
            cards.addAll(removedCards);
        }
        Card card = cards.get(0);
        cards.remove(card);
        addToRemovedCards(card);
        return card;
    }
}
