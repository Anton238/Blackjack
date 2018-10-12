package src.blacjack.offers;

import src.blacjack.game.BlackJackGame;
import src.blacjack.players.AbstactPlayer;
import src.blacjack.players.Player;

public abstract class Offer {

    public abstract boolean acceptOffer(AbstactPlayer p);

    public abstract void performOffer(BlackJackGame game, Player p);
}
