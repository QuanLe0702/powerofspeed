package vn.aptech.powerofspeed.repository.order;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.aptech.powerofspeed.model.order.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

}
