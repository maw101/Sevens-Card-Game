package sevens.views.cli;

import sevens.model.carddeck.Card;
import sevens.model.game.SevensGame;

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
            if ((numberOfComputerPlayers < 2) || (numberOfComputerPlayers > 52)) {
                System.err.println("Number of computer players must be 0 or more, but less than or equal to the total number of players.");
                System.out.println("Try again: ");
            }
        } while ((numberOfComputerPlayers < 0) || (numberOfComputerPlayers > totalPlayers));
        return numberOfComputerPlayers;
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