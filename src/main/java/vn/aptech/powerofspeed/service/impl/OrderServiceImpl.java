package vn.aptech.powerofspeed.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.aptech.powerofspeed.model.order.Order;
import vn.aptech.powerofspeed.repository.order.OrderRepository;
import vn.aptech.powerofspeed.service.OrderService;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> findAllOrder() {
        return (List<Order>) orderRepository.findAll();
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Optional<Order> findOrderById(Long id) {
        return orderRepository.findById(id);
    }
}
