package vn.aptech.powerofspeed.controller.v1.ui.frontend;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import vn.aptech.powerofspeed.dto.model.user.UserDto;
import vn.aptech.powerofspeed.model.products.Product;
import vn.aptech.powerofspeed.model.review.Review;
import vn.aptech.powerofspeed.model.user.User;
import vn.aptech.powerofspeed.service.ProductService;
import vn.aptech.powerofspeed.service.ReviewService;
import vn.aptech.powerofspeed.service.UserService;

import java.security.Principal;

@Controller
public class ShopController {
    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReviewService reviewService;

    @RequestMapping(value = "/shop",method = RequestMethod.GET)
    public ModelAndView product() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("frontend/layout/pages/shop");
        modelAndView.addObject("products", productService.findAllPro());
        return modelAndView;
    }

    @RequestMapping(value = "/productDetail/{productId}", method = RequestMethod.GET)
    public String detailProduct(Model model, @PathVariable("productId") Long id){
        Product productDetail = productService.findPk(id);
        model.addAttribute("product", productDetail);
        Review review = new Review();
        review.setProduct(productDetail);
        model.addAttribute("review", review);
        return "frontend/layout/pages/productDetail";
    }

    @RequestMapping(value="sendReview/{productId}", method = RequestMethod.POST)
    public String sendReview(Model model, @PathVariable("productId") Long id, Principal principal){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserDto user = userService.findByEmail(email);
        Product product = productService.findPk(id);
        Review review = new Review();
        review.setUser(new ModelMapper().map(user, User.class));
        review.setProduct(product);
        reviewService.create(review);
        return "redirect:/productDetail/" + product.getId();

    }
}
