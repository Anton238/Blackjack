package src.blacjack.players;

import src.blacjack.bet.Bet;
import src.blacjack.casino.cards.AceCard;
import src.blacjack.casino.cards.Card;
import src.blacjack.offers.OffersTypes;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    OffersTypes offerType;
    private List<Card> handCards;
    private int sumOfCards;
    private List<Bet> bets;

    public Hand() {
        bets = new ArrayList<>();
        handCards = new ArrayList<>();
    }

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }

    public List<Card> getHandCards() {
        return handCards;
    }

    public int getSumOfCards() {
        return sumOfCards;
    }

    public OffersTypes getOfferType() {
        return offerType;
    }

    public void setOfferType(OffersTypes offer) {
        this.offerType = offer;
    }

    public void addCardToHand(Card card) {
        handCards.add(card);
        if (sumOfCards + 11 > 21 && card instanceof AceCard) {
            card.setCardValue(1);
        }
        sumOfCards += card.getCardValue();
    }

    public boolean isBlackJack() {
        if (this.handCards.size() > 2) return false;
        return (handContainsAce() && handContainsNumber(10));

    }

    public boolean handContainsAce() {
        for (Card card : handCards)
            if (card instanceof AceCard) return true;

        return false;
    }

    public boolean handContainsNumber(int value) {
        for (Card card : handCards)
            if (card.getCardValue() == value) return true;

        return false;
    }

    public boolean isBurst() {
        return sumOfCards > 21;
    }

    public boolean isSplit() {
        if (handCards.size() >= 2) return splitCondition();
        else return false;
    }

    public boolean splitCondition() {
        return ((handCards.get(0).getClass() == handCards.get(1).getClass())) &&
                (handCards.get(0).getCardValue() == handCards.get(1).getCardValue());
    }

    public boolean isDouble() {
        return handContainsNumber(10) || handContainsNumber(11);
    }

    public boolean isHit() {
        return sumOfCards <= 16 && offerType != OffersTypes.Double;
    }

    public void clearHand() {
        bets = new ArrayList<>();
        handCards = new ArrayList<>();
        sumOfCards = 0;
    }


    @Override
    public String toString() {
        return "Hand{" +
                "handCards = " + handCards.toString() +
                ", sumOfCards = " + sumOfCards +
                ", Bets: " + bets.toString() +
                '}';
    }
}
