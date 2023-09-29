package vn.aptech.powerofspeed.services.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.aptech.powerofspeed.model.orderdetail.OrderDetail;
import vn.aptech.powerofspeed.repository.order.OrderDetailRepository;
import vn.aptech.powerofspeed.services.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public List<OrderDetail> findAll() {
        return (List<OrderDetail>) orderDetailRepository.findAll();
    }
}
