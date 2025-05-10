package main;

public class AppGamingBasic{
    public static void main(String[] args) {
        /*
        느슨한결합(Loose Coupling) 예제
        - GameRunner 클래스가 GameConsoleInterface 인터페이스에 의존한다.
        - 구체적인 구현체(MarioGame, PacManGame)를 쉽게 교체할 수 있다.
        - GameRunner 클래스의 코드 수정 없이 다양한 게임을 실행할 수 있다.
        - 이러한 방식은 코드 재사용성을 높이고 유지보수를 용이하게 만든다.
         */

       var game = new MarioGame();
        // var game = new PacManGame();
        // var game = new AnotherGame();
        var gameRunner = new GameRunner(game);
        gameRunner.run();
    }
}