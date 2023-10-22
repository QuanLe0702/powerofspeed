package vn.aptech.powerofspeed.repository.order;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.aptech.powerofspeed.model.order.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "select id from orders o order by o.id desc limit 1", nativeQuery = true)
    int getNextId();

    List<Order> findOrderByEmail(String email);
}
