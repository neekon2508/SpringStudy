package chap4.creation.initmethod;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class Singer {
    private static final String DEFAULT_NAME = "No Name";
    private String name;
    private int age;

    public void setName(String name) {
        System.out.println("Calling setName for bean of type "+Singer.class);
        this.name = name;
    }

    public void setAge(int age) {
        System.out.println("Calling setAge for bean of type "+Singer.class);
        this.age = age;
    }

    public void init() {
        System.out.println("Initializing bean");
        if (name == null) {
            System.out.println("Using default name");
            name = DEFAULT_NAME;
        }
        if (age == Integer.MIN_VALUE)
            throw new IllegalArgumentException(
                "You must set the age property of any beans of type "+Singer.class
            );
    }

    @Override
    public String toString() {
        return Singer.class.getName()+": name:"+name+", age:"+age;
    }
}
public class InitMethodDemo {
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
