package vn.aptech.powerofspeed.services.service;

import java.util.List;

import vn.aptech.powerofspeed.model.orderdetail.OrderDetail;

public interface OrderDetailService {
    OrderDetail save(OrderDetail orderDetail);
    List<OrderDetail> findAll();
}
