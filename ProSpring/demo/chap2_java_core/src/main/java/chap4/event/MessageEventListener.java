package chap4.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MessageEventListener implements ApplicationListener<MessageEvent>{

    private static Logger logger = LoggerFactory.getLogger(MessageEventListener.class);
    @Override
    public void onApplicationEvent(MessageEvent event) {
        MessageEvent msgEvt = event;
        logger.info("Received: {}", event.getMessage());
    }
    

}
