package vn.aptech.powerofspeed.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.aptech.powerofspeed.model.review.Review;
import vn.aptech.powerofspeed.repository.review.ReviewRepository;
import vn.aptech.powerofspeed.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Override
    public Review create(Review review) {
        return reviewRepository.save(review);
    }
   
    
    @Override
    public void delete(Long id) {
        Review review = reviewRepository.findById(id).get();

        reviewRepository.delete(review);
    }
}
