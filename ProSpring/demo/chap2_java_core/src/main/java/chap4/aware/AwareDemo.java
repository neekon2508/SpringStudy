package chap4.aware;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
class AwareConfig {
    @Bean
    NamedSinger johnMayer() {
        return new NamedSinger();
    }
}

public class AwareDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AwareConfig.class);
        ctx.registerShutdownHook();

        NamedSinger singer = ctx.getBean(NamedSinger.class);
        singer.sing();
    }
}
