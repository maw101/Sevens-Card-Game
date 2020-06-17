package sevens.model.players;

import sevens.model.carddeck.Card;
import sevens.model.game.SevensGame;

public interface Player {

    public Card getMove(SevensGame model, int currentPlayerNumber);

}