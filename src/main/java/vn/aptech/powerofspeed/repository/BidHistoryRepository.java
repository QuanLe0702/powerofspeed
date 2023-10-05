package vn.aptech.powerofspeed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.aptech.powerofspeed.model.products.BidHistory;

public interface BidHistoryRepository extends JpaRepository<BidHistory, Long> {
}
