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

    public void setLowestCard(Card lowestCard) {
        this.lowestCard = lowestCard;
    }

    public void setHighestCard(Card highestCard) {
        this.highestCard = highestCard;
    }

    public void setSuit(int suit) {
        this.suit = suit;
    }

}