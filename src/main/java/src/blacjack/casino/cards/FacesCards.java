package src.blacjack.casino.cards;

public class FacesCards extends Card {

    private CardPictureType cardPictureType;

    FacesCards(CardPictureType cardPictureType, CardColorType colorType) {
        super(10, colorType);
        this.cardPictureType = cardPictureType;
    }

    @Override
    public String toString() {
        return "FacesCards{" +
                "cardPictureType = " + cardPictureType +
                ", color = " + super.getColorType() +
                '}';
    }
}
