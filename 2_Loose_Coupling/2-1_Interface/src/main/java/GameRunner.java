package main;

public class GameRunner {
//    private MarioGame game;
    private GameConsoleInterface game;

    public GameRunner(GameConsoleInterface game) {
        this.game = game;
    }

    public void run() {
        System.out.println("Running Game: " + game);
        game.up();
        game.down();
        game.left();
        game.right();
    }
}
