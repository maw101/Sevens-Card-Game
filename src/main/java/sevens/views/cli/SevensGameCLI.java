package sevens.views.cli;

import sevens.model.carddeck.Card;
import sevens.model.carddeck.Hand;
import sevens.model.game.PlacedSuit;
import sevens.model.game.SevensGame;
import sevens.model.players.ComputerPlayer;
import sevens.model.players.Player;

import java.util.Scanner;

public class SevensGameCLI {

    private final SevensGame model;
    private final int numberOfComputerPlayers;
    private final static Scanner in = new Scanner(System.in);

    public SevensGameCLI() {
        int totalPlayers = getNumberOfPlayersAsInput();
        // setup new game with that number of players
        model = new SevensGame(totalPlayers);
        numberOfComputerPlayers = getNumberOfComputerPlayersAsInput();
    }

    private int getNumberOfPlayersAsInput() {
        System.out.println("Enter the Number of Players: ");
        int numberOfPlayers;
        do {
            numberOfPlayers = in.nextInt();
            if ((numberOfPlayers < 2) || (numberOfPlayers > 52)) {
                System.err.println("Number of players must be greater than 1 and less than 53");
                System.out.println("Try again: ");
            }
        } while ((numberOfPlayers < 2) || (numberOfPlayers > 52));
        return numberOfPlayers;
    }

    private int getNumberOfComputerPlayersAsInput() {
        int totalPlayers = this.model.getNumberOfPlayers();
        System.out.println("Enter the Number of Players you wish to be Computer Players: ");
        int numberOfComputerPlayers;
        do {
            numberOfComputerPlayers = in.nextInt();
            if ((numberOfComputerPlayers < 0) || (numberOfComputerPlayers > 52)) {
                System.err.println("Number of computer players must be 0 or more, but less than or equal to the total number of players.");
                System.out.println("Try again: ");
            }
        } while ((numberOfComputerPlayers < 0) || (numberOfComputerPlayers > totalPlayers));
        return numberOfComputerPlayers;
    }

    public void playGame() {
        boolean gameWon = false;
        boolean validMove;
        Card cardToPlay;
        int currentPlayerNumber;
        Player currentPlayer;

        // main game loop
        while (!gameWon) {
            currentPlayerNumber = model.getCurrentPlayerNumber();

            // display game state
            displayGameState();

            // determine our current player type
            if (currentPlayerNumber > (model.getNumberOfPlayers() - numberOfComputerPlayers)) { // is computer player
                currentPlayer = new ComputerPlayer();
            } else {
                currentPlayer = new HumanPlayerCLI();
                // display human player's hand
                displayHand(currentPlayerNumber);
            }

            // get move - loop until a valid move found
            do {
                // determine move to play
                cardToPlay = currentPlayer.getMove(model, currentPlayerNumber);
                // check player didn't skip their go
                if (cardToPlay != null) {
                    validMove = isValidMove(cardToPlay);
                    if (!validMove) {
                        System.err.println(cardToPlay + " cannot be played. Invalid Move.");
                    }

                // player skipped their go
                } else {
                    break;
                }
            } while (!validMove);

            // if card played, make move, check for win
            if (cardToPlay != null) {
                System.out.println("Playing: " + cardToPlay);
                // make the move
                model.makeMove(cardToPlay);
                // check if the player has won the game
                gameWon = hasPlayerWon();
            }
            // move to next player if no win
            if (!gameWon) {
                model.nextPlayer();
            }
        }

        // display game state
        displayGameState();
        System.out.println("Game Over");
    }

    private void displayHand(int playerNumber) {
        Hand playersHand = model.getPlayersHand(playerNumber);
        boolean canPlay;
        playersHand.sortBySuit();

        System.out.println("Player #" + playerNumber + "'s Hand:");
        for (Card card : playersHand.getHand()) {
            canPlay = model.isValidMove(card);
            // highlight card as can be played
            if (canPlay) {
                System.out.println("* \t" + card.toString());
            } else {
                System.out.println("\t" + card.toString());
            }
        }
    }

    private void displayGameState() {
        int currentPlayerNumber = model.getCurrentPlayerNumber();
        Hand[] playersHands = model.getHands();

        System.out.println("\n### GAME STATE FOLLOWS ###");

        // print current players number
        System.out.println("Current Player is #" + currentPlayerNumber);

        // print state of placed suits
        System.out.println("State of Placed Cards:");
        for (PlacedSuit suit : model.getPlayedCards()) {
            System.out.println("\t" + suit.toString());
        }

        // print basic state of each players hand - not revealing specific cards
        System.out.println("State of Players Hands:");
        for (int handIndex = 0; handIndex < playersHands.length; handIndex++) {
            System.out.println("\tPlayer " + (handIndex + 1) + " has " + playersHands[handIndex].getCardCount() + " card(s) left");
        }

        System.out.println("######\n");
    }

    private boolean hasPlayerWon() {
        int currentPlayerNumber = model.getCurrentPlayerNumber();
        if (model.hasPlayerWon(currentPlayerNumber)) {
            System.out.println("Player #" + currentPlayerNumber + " has WON!");
            return true;
        }
        return false;
    }

    private boolean isValidMove(Card move) {
        if (!model.isValidMove(move)) {
            System.err.println("Invalid Move!");
            return false;
        }
        return true;
    }

}