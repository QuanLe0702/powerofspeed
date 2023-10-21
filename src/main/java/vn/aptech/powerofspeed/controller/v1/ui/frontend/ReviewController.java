// package vn.aptech.powerofspeed.controller.v1.ui.frontend;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.ui.Model;

// import vn.aptech.powerofspeed.model.review.Review;
// import vn.aptech.powerofspeed.repository.review.ReviewRepository;

// @Controller
// public class ReviewController {

//     @Autowired
//     private ReviewRepository reviewRepository;

//     // @GetMapping(value = "/showFeedback/{id}")
//     // public List<Review> showFeedback(@PathVariable Long id Model model) {

//     // return model.addAttribute("customer", reviewRepository.findByProductId(id));
//     // // return reviewRepository.findByProductId(id);
//     // }

//     @GetMapping(value = "/showFeedback/{id}")
//     public String showFeedback(@PathVariable Long id, Model model) {
//         List<Review> reviews = reviewRepository.findByProductId(id);
//         model.addAttribute("review", reviews);
//         // return "feedback";
//         return "/review";
//     }
// }
