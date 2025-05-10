package main;

public class AppGamingBasic{
    public static void main(String[] args) {
        /*
        강한 결합(Tight Coupling) 예제
        - GameRunner 클래스가 PacManGame 클래스의 구체적인 구현에 직접 의존한다.
        - 다른 게임(예: MarioGame)으로 변경하려면 GameRunner 클래스의 코드를 직접 수정해야 한다.
        - 이러한 방식은 코드 재사용성을 낮추고 유지보수를 어렵게 만든다.
        - 결합도가 높을수록 변경에 따른 영향도 커진다.
         */
//        var marioGame = new MarioGame();
        var pacManGame = new PacManGame();
        var gameRunner = new GameRunner(pacManGame);
        gameRunner.run();
    }
}