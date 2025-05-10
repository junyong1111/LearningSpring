package main;

public class GameRunner {
//    private MarioGame game;
    private PacManGame game;

    public GameRunner(PacManGame game) {
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
