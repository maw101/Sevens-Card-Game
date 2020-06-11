// TODO: add file header
package sevens.model.game;

import sevens.model.carddeck.Card;
import sevens.model.carddeck.Deck;
import sevens.model.carddeck.Hand;

import java.util.ArrayList;

// TODO: add class JavaDoc
public class SevensGame {

    private int numberOfPlayers;
    private Hand[] hands;
    private PlacedSuit[] playedCards; // Index 0=DIAMONDS; 1=CLUBS; 2=HEARTS; 3=SPADES
    private int currentPlayerNumber;

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
        this.currentPlayerNumber = 1;
        setupNewGame();
    }

    // TODO: add method JavaDoc
    public boolean isValidMove(Card cardToPlay) {
        // get cards suit
        int cardsSuit = cardToPlay.getSuit();
        // get index of current suit
        int suitIndex = cardsSuit - 1;

        // lookup the suit in the playedCards array
        PlacedSuit placedSuit = playedCards[suitIndex];

        return placedSuit.canCardBePlaced(cardToPlay);
    }

    // TODO: add method JavaDoc
    public ArrayList<Card> getAllValidMoves(int playerNumber) {
        // create a new list to keep track of valid moves
        ArrayList<Card> validMoves = new ArrayList<>();

        // get player index and determine if the player number arg is valid
        int playerIndex = playerNumber - 1;
        checkIsValidPlayerIndex(playerIndex);
        // get the players hand
        Hand playersHand = hands[playerIndex];

        // iterate over each card in the players hand
        for (Card move : playersHand.getHand()) {
            // determine if can be played, if so add to our list of valid moves
            if (isValidMove(move)) {
                validMoves.add(move);
            }
        }

        return validMoves;
    }

    // TODO: add method JavaDoc
    private void checkIsValidPlayerIndex(int playerIndex)
        throws IllegalArgumentException {
        if ((playerIndex < 0) || (playerIndex >= numberOfPlayers)) {
            throw new IllegalArgumentException("Invalid Player Number");
        }
    }

    // TODO: add method JavaDoc
    public boolean hasPlayerWon(int playerNumber) {
        // get player index and determine if the player number arg is valid
        int playerIndex = playerNumber - 1;
        checkIsValidPlayerIndex(playerIndex);

        // determine if the player has no cards left to play
        return hands[playerIndex].getCardCount() == 0;
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
    public int getCurrentPlayerNumber() {
        return currentPlayerNumber;
    }

    // TODO: add method JavaDoc
    public void nextPlayer() {
        this.currentPlayerNumber++;
    }

    // TODO: add method JavaDoc
    public Hand getPlayersHand(int playerNumber) {
        int playerIndex = playerNumber - 1;
        checkIsValidPlayerIndex(playerIndex); // determine if the player number is valid
        return hands[playerIndex];
    }

    // TODO: add method JavaDoc
    public void makeMove(Card cardToPlay) {
        // remove card from players hand
        removeCardFromPlayersHand(currentPlayerNumber, cardToPlay);
        // play the valid move
        placeCard(cardToPlay);
    }

    // TODO: add method JavaDoc
    private void placeCard(Card cardToPlay) {
        // assumes is valid move

        // get cards suit and rank
        int cardsSuit = cardToPlay.getSuit();
        int cardsRank = cardToPlay.getRank();

        // get index of cards suit
        int suitIndex = cardsSuit - 1;

        // lookup the suit in the playedCards array
        PlacedSuit placedSuit = playedCards[suitIndex];

        // if card to play is a seven update both highest and lowest
        if (cardsRank == 7) {
            placedSuit.setLowestCard(cardToPlay);
            placedSuit.setHighestCard(cardToPlay);

        // if card to play is greater than a seven update highest
        } else if (cardsRank > 7) {
            placedSuit.setHighestCard(cardToPlay);

        // if card to play is less than than a seven update lowest
        } else { // cardsRank < 7
            placedSuit.setLowestCard(cardToPlay);
        }
    }

    // TODO: add method JavaDoc
    private void removeCardFromPlayersHand(int playerNumber, Card cardToRemove) {
        int playerIndex = playerNumber - 1;
        checkIsValidPlayerIndex(playerIndex);

        hands[playerIndex].removeCard(cardToRemove);
    }

}