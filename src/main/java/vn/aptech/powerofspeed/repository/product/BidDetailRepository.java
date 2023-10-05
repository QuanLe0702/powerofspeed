package vn.aptech.powerofspeed.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.aptech.powerofspeed.model.products.BidDetail;

@Repository
public interface BidDetailRepository extends JpaRepository<BidDetail, Long> {
}
