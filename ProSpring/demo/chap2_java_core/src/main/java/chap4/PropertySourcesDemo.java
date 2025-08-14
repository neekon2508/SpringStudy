package chap4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

import com.example.MessageProvider;
import com.example.MessageRenderer;

public class PropertySourcesDemo {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(PropertySourcesCfg.class);
        MessageRenderer mr = ctx.getBean("messageRenderer", MessageRenderer.class);
        mr.render();
    }
}

@Configuration
@PropertySource(value = "classpath:message.properties")
class PropertySourcesCfg {
    @Autowired
    Environment env;

    @Bean
    @Lazy
    public MessageProvider messageProvider() {
        return new ConfigurableMessageProvider(env.getProperty("message"));
    }

    @Bean(name = "messageRenderer")
    @Scope(value = "prototype")
    @DependsOn(value = "messageProvider")
    public MessageRenderer messageRenderer() {
        MessageRenderer renderer = new StandardOutMessageRenderer();
        renderer.setMessageProvider(messageProvider());
        return renderer;
    }
}

class ConfigurableMessageProvider implements MessageProvider {
    private String message;
    public ConfigurableMessageProvider(@Value("Configurable message") String message) {
    this.message = message;
    }
    @Override
    public String getMessage() {
       return message;
    }
}

 class StandardOutMessageRenderer implements MessageRenderer {
 private static Logger logger = LoggerFactory.getLogger(StandardOutMessageRenderer.class);
 private MessageProvider messageProvider;
 @Override
 public void setMessageProvider(MessageProvider provider) {
 this.messageProvider = provider;
 }
 @Override
 public MessageProvider getMessageProvider() {
 return this.messageProvider;
 }
 @Override
 public void render() {
 logger.info(messageProvider.getMessage());
 }
 }