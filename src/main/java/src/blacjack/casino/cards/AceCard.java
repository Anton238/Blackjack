package src.blacjack.casino.cards;

public class AceCard extends Card {

    public AceCard(CardColorType colorType) {
        super(11, colorType);
    }

    @Override
    public String toString() {
        return "AceCard{" +
                "color = " + super.getColorType() +
                '}';
    }
}
