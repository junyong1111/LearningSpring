
import org.springframework.context.annotation.Configuration;

record Person(String name, int age){};
record Address(String street, String city, String zipcode){};

@Configuration
public class HelloWorldConfiguration {
    @Bean
    public String name(){
        return "BEjun";
    }

    @Bean
    public int age(){
        return 30;
    }

    @Bean
    public Person person(){
        var person = new Person("FEjun", 20);
        return person;
    }
    @Bean
    public Person person2(){
        var person = new Person(name(), age());
        return person;
    }
    @Bean
    public Person person3(String name, int age){
        var person = new Person(name, age);
        return person;
    }

    @Bean
    public Address address(){
        var address = new Address("123 Main St", "Anytown", "12345");
        return address;
    }

}
