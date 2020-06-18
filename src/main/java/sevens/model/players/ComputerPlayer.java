package sevens.model.players;

import sevens.model.carddeck.Card;
import sevens.model.carddeck.Hand;
import sevens.model.game.PlacedSuit;
import sevens.model.game.SevensGame;

import java.util.*;

public class ComputerPlayer
        implements Player {

    public Card getMove(SevensGame model, int currentPlayerNumber) {
        return null;
    }

    private int scoreCard(SevensGame model, Hand playersHand, Card assessedCard) {
        return 0;
    }

    private int lookBelowSeven(PlacedSuit suitsState, Set<Integer> cardsOfSuitInHand) {
        // get how many cards away the furthest card is
        int minCardRank = Collections.min(cardsOfSuitInHand);
        // count cards below 7 in hand
        int cardsBelowSevenCount = (int) cardsOfSuitInHand.parallelStream().filter(rank -> rank < 7).count();

        if (cardsBelowSevenCount == 0) {
            return 0;
        } else {
            // update score to be lowest current placed rank - minCardRank - 1
            int lowestCard = (suitsState.getLowestCard() == null) ? 7 : suitsState.getLowestCard().getRank();
            int score = lowestCard - minCardRank - 1;
            // subtract number of cards remaining to play below 7 minus 1 (-1 to disregard the
            // assessed card)
            //  if the player has more cards below the current lowest played then we don't depend
            //  on other players as much - prefer to play the ones we depend on them making the
            //  most moves to allow us to play
            score -= (cardsBelowSevenCount - 1);
            // subtract the number of cards other players can place after we've reached our lowest
            // card
            score -= lowestCard - Card.ACE;
            return score;
        }
    }

    private int lookAboveSeven(PlacedSuit suitsState, Set<Integer> cardsOfSuitInHand) {
        // get how many cards away the furthest card is
        int maxCardRank = Collections.max(cardsOfSuitInHand);
        // count cards above 7 in hand
        int cardsAboveSevenCount = (int) cardsOfSuitInHand.parallelStream().filter(rank -> rank > 7).count();

        if (cardsAboveSevenCount == 0) {
            return 0;
        } else {
            // update score to be maxCardRank - highest current placed rank - 1
            int highestCard = (suitsState.getHighestCard() == null) ? 7 : suitsState.getHighestCard().getRank();
            int score = maxCardRank - highestCard - 1;
            // subtract number of cards remaining to play above 7 minus 1 (-1 to disregard the
            // assessed card)
            //  if the player has more cards above the current highest played then we don't depend
            //  on other players as much - prefer to play the ones we depend on them making the
            //  most moves to allow us to play
            score -= (cardsAboveSevenCount - 1);
            // subtract the number of cards other players can place after we've reached our highest
            // card
            score -= Card.KING - maxCardRank;
            return score;
        }
    }

}