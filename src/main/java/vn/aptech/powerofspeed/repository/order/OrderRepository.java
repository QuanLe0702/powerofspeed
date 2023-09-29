package vn.aptech.powerofspeed.repository.order;

import org.springframework.data.repository.CrudRepository;

import vn.aptech.powerofspeed.model.order.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
    
}
