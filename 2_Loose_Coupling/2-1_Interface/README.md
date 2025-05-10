# 2-1. 자바 인터페이스를 이용한 느슨한결합

## 개념 정리

느슨한결합이란 객체 간의 상호 의존성을 최소화하여 유연하고 확장 가능한 시스템을 구축하는 방식이다. 인터페이스를 통해 객체 간의 관계를 정의함으로써 구현체의 변경이 다른 객체에 영향을 미치지 않도록 한다.

### 느슨한결합의 특징
- 구체적인 클래스가 아닌 인터페이스에 의존한다.
- 구현체를 쉽게 교체할 수 있다.
- 코드 재사용성이 높아지고 유지보수가 용이해진다.
- 테스트가 쉬워진다.

## 프로젝트 예제 분석

느슨한결합을 구현하기 위해 `GameConsoleInterface` 인터페이스를 도입하였다:

```java
public interface GameConsoleInterface {
    void up();
    void down();
    void left();
    void right();
}
```

각 게임(`MarioGame`, `PacManGame`)은 이 인터페이스를 구현한다:

```java
public class MarioGame implements GameConsoleInterface {
    // 메서드 구현
}

public class PacManGame implements GameConsoleInterface {
    // 메서드 구현
}
```

`GameRunner` 클래스는 이제 구체적인 게임 클래스 대신 인터페이스에 의존한다:

```java
public class GameRunner {
    private GameConsoleInterface game;

    public GameRunner(GameConsoleInterface game) {
        this.game = game;
    }

    public void run() {
        // 메서드 호출
    }
}
```

메인 애플리케이션에서는 구현체를 쉽게 교체할 수 있다:

```java
// MarioGame 실행
var game = new MarioGame();
var gameRunner = new GameRunner(game);
gameRunner.run();

// PacManGame 실행으로 변경 (GameRunner 코드 수정 없이)
var game = new PacManGame();
var gameRunner = new GameRunner(game);
gameRunner.run();
```

## 느슨한결합의 장점

1. **유연성**: 게임을 변경하려 할 때 `GameRunner` 클래스의 코드를 수정할 필요가 없다.
2. **확장성**: 새로운 게임을 추가할 때 인터페이스만 구현하면 된다.
3. **테스트 용이성**: 목(Mock) 객체를 사용하여 쉽게 테스트할 수 있다.
4. **모듈성**: 각 모듈이 독립적으로 개발되고 결합될 수 있다.

## 일상생활의 예시

1. **USB 포트**: 다양한 USB 장치를 동일한 포트에 연결할 수 있다(인터페이스 역할).
2. **리모컨과 가전제품**: 표준화된 인터페이스(적외선/블루투스)를 통해 다양한 가전제품을 제어할 수 있다.