package tacos.kitchen.messaging.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;
import tacos.Order;
import tacos.kitchen.messaging.OrderReceiver;

@Component
public class JmsOrderReceiver implements OrderReceiver {
    private JmsTemplate jms;

    @Autowired
    public JmsOrderReceiver(JmsTemplate jms, MessageConverter converter) {
        this.jms = jms;
    }

    @Override
    public Order receiverOrder() {
        return (Order) jms.receiveAndConvert("tacocloud.order.queue");
    }
}
