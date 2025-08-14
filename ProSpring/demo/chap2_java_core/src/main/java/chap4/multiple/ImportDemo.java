package chap4.multiple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

import com.example.MessageProvider;
import com.example.MessageRenderer;

@Service("provider")
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

@Configuration
@ComponentScan
class ServiceConfig {

}

@Configuration
@Import(ServiceConfig.class)
class TheOtherConfig {
    @Autowired
    MessageProvider provider;

    @Bean(name="messageRenderer")
    public MessageRenderer messageRenderer() {
        MessageRenderer renderer = new StandardOutMessageRenderer();
        renderer.setMessageProvider(provider);
        return renderer;
    }
}

public class ImportDemo {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(TheOtherConfig.class);
        MessageRenderer mr = ctx.getBean("messageRenderer", MessageRenderer.class);
        mr.render();
    }
}
