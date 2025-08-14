package chap4.creation.jsr250;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

class Singer {
    private static final String DEFAULT_NAME = "No Name";
    private String name;
    private int age = Integer.MIN_VALUE;

    public void setName(String name) {
        System.out.println("Calling setName for bean of type "+Singer.class);
        this.name = name;
    }

    public void setAge(int age) {
        System.out.println("Calling setAge for bean of type "+Singer.class);
        this.age = age;
    }

    @Override
    public String toString() {
        return Singer.class.getName()+": name:"+name+", age:"+age;
    }

    @PostConstruct
    public void postConstruct() throws Exception {
                System.out.println("Initializing bean");
        if (name == null) {
            System.out.println("Using default name");
            name = DEFAULT_NAME;
        }
        if (age == Integer.MIN_VALUE)
            System.out.println("Using min value");
    }
}
@Configuration
class SingerConfiguration {
    @Bean()
    Singer singerOne() {
        Singer singer = new Singer();
        singer.setName("John Mayer");
        singer.setAge(43);
        return singer;
    }

    @Bean()
    Singer singerTwo() {
        Singer singer = new Singer();
        singer.setAge(42);
        return singer;
    }

    @Bean()
    Singer singerThree() {
        Singer singer = new Singer();
        singer.setName("John Bulter");
        return singer;
    }

}
public class InitializingBeanDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SingerConfiguration.class);
        getBean("singerOne", ctx);
        getBean("singerTwo", ctx);
        getBean("singerThree", ctx);
    }

    public static Singer getBean(String beanName, ApplicationContext ctx) {
        try {
            Singer bean = (Singer) ctx.getBean(beanName);
            System.out.println("Found: "+bean);
            return bean;
        } catch (BeanCreationException ex) {
            System.out.println("An error occurred in bean configuration: "+ex.getMessage());
            return null;
        }
    }
}
