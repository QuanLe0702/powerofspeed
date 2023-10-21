package vn.aptech.powerofspeed.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.aptech.powerofspeed.model.orderdetail.OrderDetail;

@Repository
public interface OrderDetailRepository extends CrudRepository<OrderDetail,Long> {

    Boolean  existsByUserIdAndProductId(Long userId, Long productId);
}
