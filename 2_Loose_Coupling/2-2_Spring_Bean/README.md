# 2-2. 스프링 빈을 이용한 느슨한결합

## 개념 정리

스프링 프레임워크는 의존성 주입(DI)을 통해 느슨한결합을 구현한다. 스프링 컨테이너가 객체(빈)의 생성과 의존성 주입을 관리함으로써 애플리케이션의 유연성과 유지보수성을 향상시킨다.

## 스프링 빈 (Spring Bean)

스프링 빈은 스프링 IoC 컨테이너에 의해 관리되는 객체이다. 다음과 같은 방법으로 정의할 수 있다:

1. **어노테이션 기반**: `@Component`, `@Service`, `@Repository`, `@Controller` 등의 어노테이션 사용
2. **자바 구성 클래스**: `@Configuration` 클래스에서 `@Bean` 메서드 사용
3. **XML 구성**: XML 파일에서 빈 정의

## 예제 코드 (향후 구현 예정)

```java
@Configuration
public class GamingConfiguration {

    @Bean
    public GameConsoleInterface marioGame() {
        return new MarioGame();
    }

    @Bean
    public GameConsoleInterface pacManGame() {
        return new PacManGame();
    }

    @Bean
    public GameRunner gameRunner(GameConsoleInterface game) {
        return new GameRunner(game);
    }
}
```

메인 애플리케이션에서는 스프링 컨텍스트를 통해 빈을 가져와 사용한다:

```java
public class AppGamingSpring {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(GamingConfiguration.class);

        // 스프링 컨테이너에서 GameRunner 빈을 가져온다
        var gameRunner = context.getBean(GameRunner.class);
        gameRunner.run();

        // 컨텍스트 닫기
        context.close();
    }
}
```

## 스프링 빈 방식의 장점

1. **의존성 관리**: 스프링 컨테이너가 의존성 관리를 담당하므로 개발자는 비즈니스 로직에 집중할 수 있다.
2. **구성 중앙화**: 애플리케이션의 구성이 한 곳에서 관리된다.
3. **라이프사이클 관리**: 스프링이 빈의 생성, 초기화, 소멸 등 라이프사이클을 관리한다.
4. **프로파일 지원**: 환경에 따라 다른 빈을 주입할 수 있다.
5. **스코프 관리**: 싱글톤, 프로토타입 등 다양한 스코프를 지원한다.

## 인터페이스 방식과 스프링 빈 방식의 비교

| 측면 | 인터페이스 방식 | 스프링 빈 방식 |
|------|----------------|---------------|
| 구현 복잡성 | 낮음 | 약간 높음 (스프링 설정 필요) |
| 의존성 주입 | 수동 (코드에서 직접 구현체 생성) | 자동 (스프링 컨테이너가 처리) |
| 테스트 용이성 | 좋음 | 매우 좋음 (모킹, 테스트 구성 용이) |
| 확장성 | 좋음 | 매우 좋음 (다양한 스프링 기능 활용 가능) |
| 라이프사이클 관리 | 수동 | 자동 (스프링이 관리) |

## 구현 계획

다음 단계에서는 스프링 프레임워크를 활용하여 게임 예제를 스프링 빈 방식으로 구현할 예정이다.