// TODO: add file header
package sevens.model.game;

import sevens.model.carddeck.Card;

// TODO: add class JavaDoc
public class PlacedSuit {

    private Card lowestCard;
    private Card highestCard;
    private int suit;

    // TODO: add method JavaDoc
    public PlacedSuit() {
        this.lowestCard = null;
        this.highestCard = null;
        this.suit = 0;
    }

    // TODO: add method JavaDoc
    public Card getLowestCard() {
        return this.lowestCard;
    }

    // TODO: add method JavaDoc
    public Card getHighestCard() {
        return this.highestCard;
    }

    // TODO: add method JavaDoc
    public int getSuit() {
        return this.suit;
    }

    // TODO: add method JavaDoc
    public String getSuitName() {
        // create a dummy card
        Card dummyCard = new Card(this.suit, 2); // random rank
        return dummyCard.getSuitString();
    }

    // TODO: add method JavaDoc
    public void setLowestCard(Card lowestCard) {
        this.lowestCard = lowestCard;
    }

    // TODO: add method JavaDoc
    public void setHighestCard(Card highestCard) {
        this.highestCard = highestCard;
    }

    // TODO: add method JavaDoc
    public void setSuit(int suit) {
        this.suit = suit;
    }

    // TODO: add method JavaDoc
    public boolean canCardBePlaced(Card cardToPlay) {
        // get cards rank
        int cardsRank = cardToPlay.getRank();

        // if at least one card placed (including the 7)
        if ((lowestCard != null) && (highestCard != null)) {
            // if >7 then valid to play if card value is 1 more than high value
            if ((cardsRank > 7) && (cardsRank == highestCard.getRank() + 1)) {
                return true;

            // if <7 then valid to play if card value is one less than low value
            } else {
                return (cardsRank < 7) && (cardsRank == lowestCard.getRank() - 1);
            }

        // if 7 then valid to play if not already there
        } else {
            return cardsRank == 7;
        }
    }

    // TODO: add method JavaDoc
    @Override
    public String toString() {
        String suitName = getSuitName();

        // if no cards placed
        if ((lowestCard == null) || (highestCard == null)) {
            return suitName + " - suit not yet played";
        }

        int lowVal = lowestCard.getRank();
        int highVal = highestCard.getRank();

        // if 7 is the only placed card
        if ((lowVal == 7) && (highVal == 7)) {
            return suitName + " - 7 Only";

        // if at least two cards placed (including the 7)
        } else {
            return suitName + " - Low Card: " + lowestCard.getRankString() + " and High Card: " + highestCard.getRankString();
        }
    }

}