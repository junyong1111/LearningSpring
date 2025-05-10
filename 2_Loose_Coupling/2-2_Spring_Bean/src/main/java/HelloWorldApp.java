public class HelloWorldApp {
    public static void main(String[] args) {
        // 1. 스프링 컨텍스트 실행
        var context = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class);

        // 2. 스프링이 관리하기를 원하는 것을 설정(Configuration)

        // 3. 스프링이 관리하는 객체를 가져오기
        var name = context.getBean("name");
        var age = context.getBean("age");
        var person = context.getBean("person");
        var person2 = context.getBean("person2");

        System.out.println(name);
        System.out.println(age);
        System.out.println(person);
        System.out.println(person2);
    }
}
