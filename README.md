# 스프링 프레임워크 학습 프로젝트

이 프로젝트는 Spring Boot 3 & Spring Framework 6를 학습하기 위한 저장소이다.
유데미 강의 **[한글자막] Spring Boot 3 & Spring Framework 6 마스터하기! [최신판]**을 기반으로 학습을 진행한다.

## 학습 내용

### 1. 강한결합 (Tight Coupling)
객체가 다른 객체에 직접적으로 의존하는 관계를 말한다. 이는 유연성이 떨어지고 코드 재사용성이 낮아지는 문제를 발생시킨다.

### 2. 느슨한결합 (Loose Coupling)
객체 간의 상호 의존성을 최소화하여 유연하고 확장 가능한 시스템을 구축하는 방식이다. 인터페이스를 통한 의존성 관리로 구현이 쉽게 교체 가능하다.

### 3. 의존성 주입 (Dependency Injection)
객체가 필요로 하는 의존성을 외부에서 주입받는 디자인 패턴이다. 스프링의 핵심 기능 중 하나로, 코드의 결합도를 낮추고 테스트 용이성을 높인다.

### 4. IOC 컨테이너 (Inversion of Control Container)
객체의 생성과 생명주기 관리를 개발자가 아닌 프레임워크가 담당하는 컨테이너이다. 제어의 역전 원칙을 구현한다.

### 5. 애플리케이션 컨텍스트 (Application Context)
스프링 프레임워크의 핵심 컨테이너로, Bean의 생성, 관리, 제공을 담당한다. BeanFactory를 확장한 고급 컨테이너이다.

### 6. 스프링 빈 (Spring Bean)
스프링 IOC 컨테이너에 의해 관리되는 객체이다. 빈 정의, 빈 스코프, 라이프사이클을 통해 관리된다.

### 7. 오토와이어링 (Auto Wiring)
스프링이 자동으로 의존성을 주입하는 기능이다. @Autowired 어노테이션을 통해 구현되며, 개발자가 명시적으로 의존성을 설정하지 않아도 된다.

### 8. 컴포넌트 스캔 (Component Scan)
스프링이 자동으로 클래스패스에서 빈으로 등록할 컴포넌트를 검색하는 기능이다. @Component, @Service, @Repository, @Controller 등의 어노테이션이 붙은 클래스를 자동으로 빈으로 등록한다.

## 개발 환경
- Java 17+
- Spring Boot 3.x
- Spring Framework 6.x
- IDE: IntelliJ IDEA/Eclipse/VSCode 중 선택
- 빌드 도구: Gradle/Maven

## 프로젝트 구조
```
/src
  /main
    /java
      /com/example/learningspring
        /config
        /controller
        /model
        /repository
        /service
    /resources
      application.properties
  /test
```


