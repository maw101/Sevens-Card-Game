// TODO: file header
package sevens.views.cli;

import sevens.model.carddeck.Card;
import sevens.model.carddeck.Hand;
import sevens.model.game.SevensGame;
import sevens.model.players.Player;

import java.util.Scanner;

// TODO: class JavaDoc
public class HumanPlayerCLI
        implements Player {

    private final static Scanner in = new Scanner(System.in);

    // TODO: method JavaDoc
    public Card getMove(SevensGame model, int currentPlayerNumber) {
        Hand hand = model.getPlayersHand(currentPlayerNumber);
        Card card;
        int suit;
        int rank;
        boolean canMakeMove = !model.getAllValidMoves(currentPlayerNumber).isEmpty();

        // get a card from the players hand
        do {
            if (!canMakeMove) {
                System.out.println("Player " + currentPlayerNumber + ", there were no moves you could make - your go was skipped.");
                return null;
            }

            // get card input from user
            do {
                suit = getSuit();
            } while (suit == -1);
            do {
                rank = getRank();
            } while (rank == -1);

            // tries to get the card from the hand - returns null if card not present
            card = hand.getCard(suit, rank);
            if (card == null) {
                System.err.println("This card is not in your hand. Invalid Card.");
            }
        } while (card == null);
        // return the card - calling method checks if we can play this card
        return card;
    }

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

}