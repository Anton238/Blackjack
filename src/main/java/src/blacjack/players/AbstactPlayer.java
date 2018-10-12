package src.blacjack.players;

import src.blacjack.casino.cards.Card;
import src.blacjack.offers.OffersTypes;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstactPlayer {

    List<Hand> hands;
    String name;
    boolean splittedPlayer;

    public boolean isSplittedPlayer() {
        return splittedPlayer;
    }

    public void setSplittedPlayer(boolean splittedPlayer) {
        this.splittedPlayer = splittedPlayer;
    }

    AbstactPlayer() {
        this.hands = new LinkedList<>();
        hands.add(new Hand());
        hands.add(new Hand());
    }

    public String getName() {
        return name;
    }

    public List<Hand> getHands() {
        return hands;
    }

    void addCardToFirstHand(Card card) {
        hands.get(0).addCardToHand(card);
    }

    public void clearHands() {
        this.hands = new LinkedList<>();
        hands.add(new Hand());
        hands.add(new Hand());
    }

    public Hand getFirstHand() {
        return hands.get(0);
    }

    public Hand getSecondHand() {
        return hands.get(1);
    }

    public boolean isAnyOfHandsHit() {
        for (Hand hand : getHands()) {
            if (hand.isHit() && hand.offerType != OffersTypes.Double) return true;
        }
        return false;
    }

    public boolean isAllPlayersHandsAreEmpty() {
        for (Hand hand : getHands()) {
            if (hand.getHandCards().size() > 0) return false;
        }
        return true;
    }

}

