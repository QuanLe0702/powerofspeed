package vn.aptech.powerofspeed.repository.order;

import org.springframework.data.repository.CrudRepository;

import vn.aptech.powerofspeed.model.orderdetail.OrderDetail;

public interface OrderDetailRepository extends CrudRepository<OrderDetail, Long> {
    
}
