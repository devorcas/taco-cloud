package tacos.kitchen.messaging.jms.listenr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import tacos.Order;
import tacos.kitchen.messaging.KitchenUI;

@Component
@Profile("jms-listener")
public class OrderListener {
    private KitchenUI ui;

    @Autowired
    public OrderListener(KitchenUI ui){
        this.ui = ui;
    }

    @JmsListener(destination = "tacocloud.order.queue")
    public void receiveOrder(Order order){
        ui.displayOrder(order);
    }
}
