// package vn.aptech.powerofspeed.controller.v1.ui.frontend;


// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.servlet.ModelAndView;
// import vn.aptech.powerofspeed.dto.model.user.UserDto;
// import vn.aptech.powerofspeed.model.category.Category;
// import vn.aptech.powerofspeed.model.products.Product;
// import vn.aptech.powerofspeed.model.subcategory.Subcategory;
// import vn.aptech.powerofspeed.model.user.User;
// import vn.aptech.powerofspeed.service.CategoryService;
// import vn.aptech.powerofspeed.service.ProductService;
// import vn.aptech.powerofspeed.service.UserService;

// import java.util.ArrayList;
// import java.util.List;

// @Controller
// public class HomeController {

//     @Autowired
//     private UserService userService;

//     @Autowired
//     private ProductService productService;

//     @Autowired
//     private CategoryService categoryService;

//     @RequestMapping(value = {"", "/", "/index"}, method = RequestMethod.GET)
//     public String index(Model model) {
//         List<Product> productAuctionList = new ArrayList<>();
//         List<Product> productArrivals = new ArrayList<>();
//         List<Category> categories = categoryService.findAllCat();

//         if (categories.size() == 0) {
//             return "redirect:/accessDenied";
//         }

//         for (Category category : categories) {
//             for (Subcategory subcategory : category.getSubcategories()) {
//                 for (Product product : subcategory.getProducts()) {
//                     if(product.isStatus()) {
//                         if (product.getBidDetail() != null) {
//                             productAuctionList.add(product);
//                         } else {
//                             productArrivals.add(product);
//                         }
//                     }
//                 }
//             }
//         }

//         model.addAttribute("productArrivals", productArrivals);
//         model.addAttribute("productAuctionList", productAuctionList);

//         return "frontend/layout/pages/index";
//     }

//     @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
//     public ModelAndView accessDenied() {
//         ModelAndView modelAndView = new ModelAndView();
//         modelAndView.setViewName("frontend/layout/pages/accessDenied");
//         return modelAndView;
//     }
// }
