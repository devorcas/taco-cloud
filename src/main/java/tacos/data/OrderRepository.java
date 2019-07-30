package tacos.data;

import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tacos.Order;

import java.util.List;

public interface OrderRepository
        extends CrudRepository<Order, Long> {
    List<Order> findByZip(String deliveryZip);

}
