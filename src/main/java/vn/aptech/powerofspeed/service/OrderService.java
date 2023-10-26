package vn.aptech.powerofspeed.service;

import vn.aptech.powerofspeed.model.order.Order;
import vn.aptech.powerofspeed.model.order.StatusType;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> findAllOrder();
    Order save(Order order);
    Order update(Order order);
    void deleteOrder(Long id);
    Optional<Order> findOrderById(Long id);

    List<Order> getOrderByEmail(String email);

    Order updateOrderStatus(Long orderId, StatusType status);
}
