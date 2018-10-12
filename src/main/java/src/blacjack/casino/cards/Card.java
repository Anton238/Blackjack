package src.blacjack.casino.cards;

public class Card {

    private int cardValue;
    private CardColorType colorType;

    public Card(int cardValue, CardColorType colorType) {
        this.cardValue = cardValue;
        this.colorType = colorType;
    }

    public Card() {
    }

    public int getCardValue() {
        return cardValue;
    }

    public void setCardValue(int cardValue) {
        this.cardValue = cardValue;
    }

    CardColorType getColorType() {
        return colorType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (getCardValue() != card.getCardValue()) return false;
        return getColorType() == card.getColorType();
    }

    @Override
    public int hashCode() {
        int result = getCardValue();
        result = 31 * result + (getColorType() != null ? getColorType().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardValue = " + cardValue +
                ", colorType = " + colorType +
                '}';
    }
}
