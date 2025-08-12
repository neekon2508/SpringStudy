package tacos.message.jms;

import org.springframework.jms.support.converter.MessageConversionException;


import jakarta.jms.JMSException;
import tacos.data.TacoOrder;

public interface OrderReceiver {

    TacoOrder receiveOrder();
}
