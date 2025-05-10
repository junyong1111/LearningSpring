# 2-2. 스프링 빈을 이용한 느슨한결합

## 개념 정리

스프링 프레임워크는 의존성 주입(DI)을 통해 느슨한결합을 구현한다. 스프링 컨테이너가 객체(빈)의 생성과 의존성 주입을 관리함으로써 애플리케이션의 유연성과 유지보수성을 향상시킨다.

## 스프링 컨테이너의 이해

### 스프링 컨테이너란?
스프링 컨테이너는 스프링 빈과 그 생명주기를 관리하는 핵심 컴포넌트이다. 컨테이너는 자바 클래스와 설정 정보를 입력으로 받아 완전히 구성된 시스템을 출력으로 제공한다. 즉, 스프링 컨테이너는 다음 작업을 담당한다:
- 빈 생성 및 관리
- 의존성 주입
- 빈 생명주기 관리
- 런타임 시스템 구성

### 스프링 컨테이너의 종류
1. **BeanFactory**:
   - 기본적인 스프링 컨테이너
   - 가볍고 기본 기능만 제공
   - 메모리 제약이 있는 IoT 애플리케이션에 적합

2. **ApplicationContext**:
   - 고급 스프링 컨테이너
   - BeanFactory를 확장한 기능 제공
   - 엔터프라이즈 수준의 기능 지원 (국제화, AOP 등)
   - 웹 애플리케이션, REST API, 마이크로서비스에 적합
   - 대부분의 엔터프라이즈 애플리케이션에서 사용

### 관련 용어
- **스프링 컨텍스트**: 스프링 컨테이너와 동일한 의미
- **IoC 컨테이너**: 제어의 역전(Inversion of Control) 원칙을 구현한 컨테이너
- **애플리케이션 컨텍스트**: ApplicationContext를 의미

## 스프링 빈 (Spring Bean)

스프링 빈은 스프링 IoC 컨테이너에 의해 관리되는 객체이다. 스프링이 객체의 생명주기를 관리하고 필요한 의존성을 주입해준다.

### Java Bean vs Spring Bean
1. **Java Bean**:
   - 자바 언어의 규약을 따르는 클래스
   - 기본 생성자, getter/setter 메서드, Serializable 인터페이스 구현 필요
   - 특정 프레임워크에 의존하지 않음

2. **Spring Bean**:
   - 스프링 IoC 컨테이너에 의해 관리되는 객체
   - Java Bean 규약을 따를 수도 있고 따르지 않을 수도 있음
   - 스프링 컨테이너에 등록되고 관리됨

### 스프링 빈 정의 방법
1. **어노테이션 기반**: `@Component`, `@Service`, `@Repository`, `@Controller` 등의 어노테이션 사용
2. **자바 구성 클래스**: `@Configuration` 클래스에서 `@Bean` 메서드 사용
3. **XML 구성**: XML 파일에서 빈 정의

### 스프링 빈 조회 방법
스프링 컨텍스트에서 빈을 조회하는 다양한 방법:

1. **이름으로 조회**:
```java
context.getBean("person") // 빈 이름으로 조회
```

2. **타입으로 조회**:
```java
context.getBean(Person.class) // 타입으로 조회 (동일 타입의 빈이 여러 개면 예외 발생)
```

3. **이름과 타입으로 조회**:
```java
context.getBean("person", Person.class) // 이름과 타입 모두 지정하여 조회
```

### 모든 스프링 빈 목록 조회
스프링 컨텍스트에서 모든 빈 목록을 조회하는 방법:

```java
String[] beanNames = context.getBeanDefinitionNames();
for (String beanName : beanNames) {
    System.out.println(beanName);
}
```

## 여러 빈이 있을 때의 처리

동일한 타입의 빈이 여러 개 있을 경우 스프링은 어떤 빈을 사용할지 결정해야 한다:

### 문제 상황
```java
@Bean(name = "address2")
public Address address() {
    return new Address("베이커 스트리트", "런던");
}

@Bean(name = "address3")
public Address address3() {
    return new Address("모티 나가르", "하이데라바드");
}

// 아래 코드는 예외 발생
// context.getBean(Address.class); // NoUniqueBeanDefinitionException
```

### 해결 방법

1. **@Primary 어노테이션 사용**:
```java
@Bean
@Primary
public Address address() {
    return new Address("베이커 스트리트", "런던");
}
```

2. **@Qualifier 어노테이션 사용**:
```java
@Bean
public Person person(@Qualifier("address2") Address address) {
    return new Person("Ravi", 20, address);
}
```

3. **이름과 타입으로 명시적 조회**:
```java
context.getBean("address2", Address.class);
```

## 객체 생성 및 의존성 주입의 전환

### 기존 방식 (수동 객체 생성 및 의존성 주입)
```java
// 1. 객체 생성
var game = new PacManGame();
// 2. 객체 생성 및 의존성 주입
var gameRunner = new GameRunner(game);
// 3. 메서드 실행
gameRunner.run();
```

이 방식에서는:
- 개발자가 직접 객체를 생성한다.
- 개발자가 직접 의존성을 주입한다.
- 개발자가 모든 객체의 생명주기를 관리한다.

### 스프링 방식 (컨테이너를 통한 객체 관리)
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

```java
public class AppGamingSpring {
    public static void main(String[] args) {
        // 스프링 컨텍스트 생성
        var context = new AnnotationConfigApplicationContext(GamingConfiguration.class);

        // 스프링 컨테이너에서 GameRunner 빈을 가져온다
        var gameRunner = context.getBean(GameRunner.class);
        gameRunner.run();

        // 컨텍스트 닫기
        context.close();
    }
}
```

이 방식에서는:
- 스프링 컨테이너가 객체를 생성한다.
- 스프링 컨테이너가 의존성을 주입한다.
- 스프링 컨테이너가 객체의 생명주기를 관리한다.

## 스프링 빈 활용 예시

### 다양한 타입의 빈 등록
스프링은 다양한 타입의 객체를 빈으로 등록하고 관리할 수 있다:

```java
@Configuration
public class HelloWorldConfiguration {

    @Bean
    public String name() {
        return "Ravi";
    }

    @Bean
    public int age() {
        return 15;
    }

    @Bean
    public Person person() {
        var person = new Person("Ravi", 20);
        return person;
    }

    @Bean
    public Address address() {
        return new Address("베이커 스트리트", "런던");
    }
}
```

```java
// 컨텍스트에서 빈 가져오기
System.out.println(context.getBean("name"));
System.out.println(context.getBean("age"));
System.out.println(context.getBean("person"));
System.out.println(context.getBean("address"));
```

### 사용자 정의 클래스 활용
```java
// JDK 16+ 레코드 기능 활용
record Person(String name, int age) {}
record Address(String firstLine, String city) {}
```

## 스프링 빈 간의 의존성 관리

스프링은 빈 간의 의존성을 다양한 방식으로 처리할 수 있다:

### 1. 메서드 호출을 통한 의존성 주입
```java
@Bean
public Person person2MethodCall() {
    var person = new Person("Ranga", 15, address()); // address() 메서드 직접 호출
    return person;
}
```

### 2. 매개변수를 통한 의존성 주입
```java
@Bean
public Person person3Parameters(String name, int age, Address address2) {
    // 스프링이 자동으로 name, age, address2 빈을 찾아 주입
    var person = new Person(name, age, address2);
    return person;
}
```

### 3. 중첩된 객체 구조 생성
```java
@Bean
public Person personWithAddress() {
    var person = new Person("Ravi", 20,
        new Address("메인스트리트", "위트레흐트")); // 내부에 새 주소 객체 생성
    return person;
}
```

### 4. 빈 재사용
```java
@Bean(name = "address2")
public Address address() {
    return new Address("베이커 스트리트", "런던");
}

@Bean(name = "address3")
public Address address3() {
    return new Address("모티 나가르", "하이데라바드");
}
```

## 스프링 빈 이름 지정

기본적으로 빈의 이름은 메서드명이 되지만, 직접 지정도 가능하다:

```java
@Bean(name = "customBeanName")
public Address address() {
    return new Address("베이커 스트리트", "런던");
}
```

## 스프링을 통한 자동 객체 생성

스프링은 개발자가 직접 객체를 생성하는 대신 자동으로 객체를 생성하도록 구성할 수 있다:

### 1. 컴포넌트 스캔
```java
@Configuration
@ComponentScan("com.example.package") // 패키지 지정
public class AppConfig {
}
```

```java
@Component // 자동으로 스프링 빈으로 등록됨
public class Person {
    private String name;
    private int age;

    // 생성자, getter, setter
}
```

### 2. 자동 의존성 주입
```java
@Component
public class GameRunner {
    private final GameConsoleInterface game;

    @Autowired // 스프링이 자동으로 의존성 주입
    public GameRunner(GameConsoleInterface game) {
        this.game = game;
    }
}
```

## 스프링 기반 느슨한결합의 장점

1. **객체 생성 관리**: 스프링이 객체 생성을 담당하므로 개발자는 비즈니스 로직에 집중할 수 있다.
2. **의존성 주입 자동화**: 스프링이 필요한 의존성을 자동으로 주입해준다.
3. **구성 중앙화**: 애플리케이션의 구성이 한 곳(Configuration 클래스)에서 관리된다.
4. **라이프사이클 관리**: 스프링이 빈의 생성, 초기화, 소멸 등 라이프사이클을 관리한다.
5. **코드 유연성**: 구현체를 변경할 때 설정 부분만 수정하면 된다.

## 엔터프라이즈 애플리케이션에서의 중요성

엔터프라이즈 애플리케이션에서는:
- 수천 개의 클래스가 존재한다.
- 수천 개의 의존성이 생성되고 주입된다.
- 수동으로 이 모든 것을 관리하는 것은 매우 복잡하고 오류가 발생하기 쉽다.

스프링 프레임워크는 이러한 복잡성을 관리하는 데 도움을 주며, 개발자가 비즈니스 로직에 집중할 수 있게 한다.

## 인터페이스 방식과 스프링 빈 방식의 비교

| 측면 | 인터페이스 방식 | 스프링 빈 방식 |
|------|----------------|---------------|
| 객체 생성 | 수동 (개발자가 직접) | 자동 (스프링 컨테이너가 관리) |
| 의존성 주입 | 수동 (코드에서 직접 구현체 생성) | 자동 (스프링 컨테이너가 처리) |
| 구성 관리 | 코드 내에 분산됨 | 중앙화됨 (설정 클래스/파일) |
| 테스트 용이성 | 좋음 | 매우 좋음 (모킹, 테스트 구성 용이) |
| 코드 변경 시 영향 | 여러 파일 수정 필요 | 설정 파일만 수정 |
| 확장성 | 좋음 | 매우 좋음 (다양한 스프링 기능 활용 가능) |

## 주의사항

1. **동일 타입의 여러 빈**:
   ```java
   // 동일 타입(Address)의 빈이 여러 개 있을 경우 타입만으로 조회 시 예외 발생
   context.getBean(Address.class); // NoUniqueBeanDefinitionException
   ```

2. **해결 방법**:
   - 이름을 명시하여 조회: `context.getBean("address2", Address.class)`
   - 빈 정의 시 기본 빈 지정: `@Primary` 어노테이션 사용
   - 자동 주입 시 `@Qualifier` 어노테이션으로 특정 빈 지정