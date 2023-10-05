package vn.aptech.powerofspeed.repository.review;

import org.springframework.data.repository.CrudRepository;
import vn.aptech.powerofspeed.model.review.Review;

public interface ReviewRepository extends CrudRepository<Review, Long> {
}
