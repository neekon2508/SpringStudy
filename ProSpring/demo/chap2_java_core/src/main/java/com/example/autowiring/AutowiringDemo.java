package com.example.autowiring;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

public class AutowiringDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AutowiringCfg.class);
        AnotherTarget target = ctx.getBean(AnotherTarget.class);
        System.out.println("Created target? "+(target!=null));
        System.out.println("Injected bar? "+ (target.bar != null));
        System.out.println("Injected fooOne? "+ (target.fooOne != null ? target.fooOne: ""));
        System.out.println("Injected fooTwo? "+ (target.fooTwo != null ? target.fooTwo: ""));
 
    }
}

@Configuration
@ComponentScan
class AutowiringCfg {

    @Bean
    public Foo foolImplOne() {
        return new FooImplOne();
    }

    @Bean
    public Foo foolImplTwo() {
        return new FooImplTwo();
    }

    @Bean
    public Bar bar() {
        return new Bar();
    }

    @Bean
    public AnotherTarget AnotherTarget() {
        return new AnotherTarget();
    }

  
}

@Component
@Lazy
class Target {
    Foo fooOne;
    Foo fooTwo;
    Bar bar;

    public Target() {
        System.out.println(" --> Target() called");
    }

    public Target(@Qualifier("foo") Foo foo) {
        this.fooOne = foo;
        System.out.println(" --> Target(Foo) called");
    }

    public Target(@Qualifier("foo") Foo foo, Bar bar) {
        this.fooOne = foo;
        this.bar = bar;
        System.out.println(" --> Target(Foo, Bar) called");
    }
}

@Component
@Lazy
class AnotherTarget {
    Foo fooOne;
    Foo fooTwo;
    Bar bar;

    @Autowired
    @Qualifier("fooImplOne")
    public void setFooOne(@Qualifier("foo") Foo fooOne) {
        System.out.println(" --> AnotherTarget#setFooOne(Foo) called");
        this.fooOne = fooOne;
    }

    @Autowired
    @Qualifier("fooImplTwo")
    public void setFooTwo(@Qualifier("anotherFoo") Foo fooTwo) {
        System.out.println(" --> AnotherTarget#setFooTwo(Foo) called");
        this.fooTwo = fooTwo;
    }

    @Autowired
    public void setBar(Bar bar) {
        System.out.println(" --> AnotherTarget#setBar(Bar) called");
        this.bar = bar;
    }

    
}
interface Foo {
    
}
class FooImplOne implements Foo {
    String id = "one:"+UUID.randomUUID().toString().replace("-", "").substring(0,8);

    @Override
    public String toString() {
        return FooImplOne.class.getName()+":"+id;
    }
}

class FooImplTwo implements Foo {
    String id = "two:"+UUID.randomUUID().toString().replace("-", "").substring(0,8);

    @Override
    public String toString() {
        return FooImplOne.class.getName()+":"+id;
    }
}

class Bar {}