package vn.aptech.powerofspeed.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.aptech.powerofspeed.model.order.Order;
import vn.aptech.powerofspeed.repository.order.OrderRepository;
import vn.aptech.powerofspeed.service.OrderService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

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

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Order update(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrderByEmail(String email) {
        return orderRepository.findOrderByEmail(email);
    }

//    @Override
//    public List<Order> findOrderedByIdLimitedTo(int limit) {
//        return entityManager.createQuery("SELECT p.id FROM Order p ORDER BY p.id",
//                Order.class).setMaxResults(limit).getResultList();
//    }


}
