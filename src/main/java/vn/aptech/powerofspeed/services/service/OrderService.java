package vn.aptech.powerofspeed.services.service;

import java.util.List;
import java.util.Optional;

import vn.aptech.powerofspeed.model.order.Order;

public interface OrderService {
    List<Order> findAllOrder();
    Order save(Order order);
    void deleteOrder(Long id);
    Optional<Order> findOrderById(Long id);
}
