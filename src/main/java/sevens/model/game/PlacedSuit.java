package sevens.model.game;

import sevens.model.carddeck.Card;

public class PlacedSuit {

    private Card lowestCard;
    private Card highestCard;
    private int suit;

    public PlacedSuit() {
        this.lowestCard = null;
        this.highestCard = null;
        this.suit = 0;
    }

    public Card getLowestCard() {
        return this.lowestCard;
    }

    public Card getHighestCard() {
        return this.highestCard;
    }

    public int getSuit() {
        return this.suit;
    }

    public String getSuitName() {
        // create a dummy card
        Card dummyCard = new Card(this.suit, 2); // random rank
        return dummyCard.getSuitString();
    }

    public void setLowestCard(Card lowestCard) {
        this.lowestCard = lowestCard;
    }

    public void setHighestCard(Card highestCard) {
        this.highestCard = highestCard;
    }

    public void setSuit(int suit) {
        this.suit = suit;
    }

    public boolean canCardBePlaced(Card cardToPlay) {
        // get cards rank
        int cardsRank = cardToPlay.getRank();

        // if at least one card placed (including the 7)
        if ((lowestCard != null) && (highestCard != null)) {
            // if >7 then valid to play if card value is 1 more than high value
            if ((cardsRank > 7) && (cardsRank == highestCard.getRank() + 1)) {
                return true;

            // if <7 then valid to play if card value is one less than low value
            } else if ((cardsRank < 7) && (cardsRank == lowestCard.getRank() - 1)) {
                return true;
            }

        // if 7 then valid to play if not already there
        } else if (cardsRank == 7) {
            return true;
        }

        return false;
    }

}