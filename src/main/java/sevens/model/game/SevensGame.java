// TODO: add file header
package sevens.model.game;

import sevens.model.carddeck.Card;
import sevens.model.carddeck.Deck;
import sevens.model.carddeck.Hand;

// TODO: add class JavaDoc
public class SevensGame {

    private int numberOfPlayers;
    private Hand[] hands;
    private PlacedSuit[] playedCards; // Index 0=DIAMONDS; 1=CLUBS; 2=HEARTS; 3=SPADES

    // Card Constants:
    //  DIAMOND = 1
    //  CLUB = 2
    //  HEART = 3
    //  SPADE = 4

    // TODO: add method JavaDoc
    public SevensGame() {
        // setup game with default number of players (4)
        this(4);
    }

    // TODO: add method JavaDoc
    public SevensGame(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        setupNewGame();
    }

    // TODO: add method JavaDoc
    private void checkIsValidPlayerIndex(int playerIndex)
        throws IllegalArgumentException {
        if ((playerIndex < 0) || (playerIndex >= numberOfPlayers)) {
            throw new IllegalArgumentException("Invalid Player Number");
        }
    }

    // TODO: add method JavaDoc
    public void setupNewGame() {
        playedCards = new PlacedSuit[4];

        // instantiate each placed suit
        for (int i = 0; i < 4; i++) {
            playedCards[i] = new PlacedSuit();
        }

        // deal cards
        performInitialDeal();
    }

    // TODO: add method JavaDoc
    private void performInitialDeal() {
        Card dealtCard;
        int currentPlayerIndex = 0;

        // setup new deck and new hand states
        Deck deck = new Deck(false); // standard deck without jokers
        hands = new Hand[numberOfPlayers];

        // instantiate each hand
        for (int i = 0; i < numberOfPlayers; i++) {
            hands[i] = new Hand();
        }

        // deal all the cards in the deck
        while (deck.getNumberOfCardsRemaining() > 0) {
            dealtCard = deck.dealCard();
            hands[currentPlayerIndex].addCard(dealtCard);

            currentPlayerIndex++;
            // if we are on the last player, cycle back around to first player
            if (currentPlayerIndex == numberOfPlayers) {
                currentPlayerIndex = 0;
            }
        }
    }

    // TODO: add method JavaDoc
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    // TODO: add method JavaDoc
    public Hand[] getHands() {
        return hands;
    }

    // TODO: add method JavaDoc
    public PlacedSuit[] getPlayedCards() {
        return playedCards;
    }

    // TODO: add method JavaDoc
    public Hand getPlayersHand(int playerNumber) {
        int playerIndex = playerNumber - 1;
        checkIsValidPlayerIndex(playerIndex); // determine if the player number is valid
        return hands[playerIndex];
    }

    // TODO: add method JavaDoc
    public void removeCardFromPlayersHand(int playerNumber, Card cardToRemove) {
        int playerIndex = playerNumber - 1;
        checkIsValidPlayerIndex(playerIndex);

        hands[playerIndex].removeCard(cardToRemove);
    }

}