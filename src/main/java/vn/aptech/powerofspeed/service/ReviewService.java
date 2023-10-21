package vn.aptech.powerofspeed.service;

import java.util.List;

import vn.aptech.powerofspeed.model.review.Review;

public interface ReviewService {
    Review create(Review review);

    


    void delete(Long id);
}
