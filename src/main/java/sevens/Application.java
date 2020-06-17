package sevens;

import sevens.views.cli.SevensGameCLI;

public class Application {

    public static void main(String[] args) {
        SevensGameCLI game = new SevensGameCLI();
        game.playGame();
    }

}