package tacos.kitchen.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tacos.Order;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderReceiverController {

    private final OrderReceiver orderReceiver;

    @GetMapping("/receive")
    public String receiveOrder(Model model) {
        Order order = orderReceiver.receiverOrder();
        if (order != null) {
            model.addAttribute("order", order);
            return "receiveOrder";
        }
        return "noOrder";
    }
}
