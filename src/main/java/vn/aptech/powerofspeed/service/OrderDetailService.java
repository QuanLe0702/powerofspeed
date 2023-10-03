package vn.aptech.powerofspeed.service;


import vn.aptech.powerofspeed.model.orderdetail.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    OrderDetail save(OrderDetail orderDetail);

    List<OrderDetail> findAll();
}
