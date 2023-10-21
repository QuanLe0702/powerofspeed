package vn.aptech.powerofspeed.repository.review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.repository.CrudRepository;
import vn.aptech.powerofspeed.model.review.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    //list review by product
    List<Review> findByProductId(Long productId);
}
