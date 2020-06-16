// TODO: file header
package sevens.views.cli;

import sevens.model.carddeck.Card;
import sevens.model.carddeck.Hand;
import sevens.model.game.SevensGame;

import java.util.Scanner;

// TODO: class JavaDoc
public class HumanPlayerCLI {

    private final static Scanner in = new Scanner(System.in);

    // TODO: method JavaDoc
    private int getSuit() {
        String suitInput;
        System.out.println("Enter the Suit (DIAMONDS, CLUBS, HEARTS, SPADES):");
        suitInput = in.nextLine().toUpperCase();
        switch (suitInput) {
            case "DIAMONDS":
                return Card.DIAMOND;
            case "CLUBS":
                return Card.CLUB;
            case "HEARTS":
                return Card.HEART;
            case "SPADES":
                return Card.SPADE;
        }
        System.err.println("Invalid Suit Entered");
        return -1;
    }

    // TODO: method JavaDoc
    private int getRank() {
        String rankInput;
        System.out.println("Enter the Rank (ACE, 2-10, JACK, QUEEN, KING):");
        rankInput = in.nextLine().toUpperCase();
        switch (rankInput) {
            case "ACE":
                return Card.ACE;
            case "JACK":
                return Card.JACK;
            case "QUEEN":
                return Card.QUEEN;
            case "KING":
                return Card.KING;
        }
        // may be a numeric card
        try {
            int rank = Integer.parseInt(rankInput);
            if ((rank > Card.ACE) && (rank < Card.JACK)) {
                return rank;
            }
        } catch (NumberFormatException | NullPointerException ne) {
            // do nothing
        }
        System.err.println("Invalid Rank Entered");
        return -1; // invalid
    }

    // TODO: method JavaDoc
    private boolean wantsToSkipGo() {
        System.out.println("Enter Y if you wish to skip your go:");
        String input = in.nextLine().toUpperCase();
        return ("Y").equals(input);
    }

}