package tacos.message.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import tacos.data.TacoOrder;




@Component
public class JmsOrderReceiver implements OrderReceiver{

    private JmsTemplate jms;

    @Autowired
    public JmsOrderReceiver(JmsTemplate jms) {
        this.jms = jms;
    }

    @Override
    public TacoOrder receiveOrder()  {
      return (TacoOrder) jms.receiveAndConvert("tacocloud.order.queue");
    }

}
