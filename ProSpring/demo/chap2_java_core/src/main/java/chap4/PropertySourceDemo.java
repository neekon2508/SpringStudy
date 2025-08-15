package chap4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
class PropDemoConfig {
    @Bean
    AppProperty appProperty() {
        return new AppProperty();
    }
}
public class PropertySourceDemo {
    private static Logger logger = LoggerFactory.getLogger(PropertySourceDemo.class);
    public static void main(String... args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(PropDemoConfig.class);
        AppProperty appProperty = ctx.getBean("appProperty", AppProperty.class);
        logger.info("Outcome: {}", appProperty);
    }
}
