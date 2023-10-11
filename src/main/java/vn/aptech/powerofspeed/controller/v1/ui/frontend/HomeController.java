package vn.aptech.powerofspeed.controller.v1.ui.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import vn.aptech.powerofspeed.controller.v1.request.SearchProductRequest;
import vn.aptech.powerofspeed.dto.model.user.UserDto;
import vn.aptech.powerofspeed.model.category.Category;
import vn.aptech.powerofspeed.model.products.Product;
import vn.aptech.powerofspeed.model.subcategory.Subcategory;
import vn.aptech.powerofspeed.model.user.User;
import vn.aptech.powerofspeed.service.CategoryService;
import vn.aptech.powerofspeed.service.ProductService;
import vn.aptech.powerofspeed.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = {"", "/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        List<Product> productAuctionList = new ArrayList<>();
        List<Product> productArrivals = new ArrayList<>();
        List<Product> productSalesPrice = productService.findAllBySalePrice();
        List<Category> categories = categoryService.findAllCat();
        SearchProductRequest searchProductRequest = new SearchProductRequest();

        if (categories.size() == 0) {
            return "redirect:/accessDenied";
        }

        for (Category category : categories) {
            for (Subcategory subcategory : category.getSubcategories()) {
                for (Product product : subcategory.getProducts()) {
                    if(product.isStatus()) {
                        if (product.getBidDetail() != null) {
                            productAuctionList.add(product);
                        } else {
                            productArrivals.add(product);
                        }
                    }
                }
            }
        }

        model.addAttribute("productSalesPrice", productSalesPrice);
        model.addAttribute("searchProductRequest", searchProductRequest);
        model.addAttribute("productArrivals", productArrivals);
        model.addAttribute("productAuctionList", productAuctionList);

        return "frontend/layout/pages/index";
    }

    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public ModelAndView accessDenied() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("frontend/layout/pages/accessDenied");
        return modelAndView;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String productSearch(Model model, @RequestParam("query") String name,
                                @RequestParam("poscats") Long subCategoryId) {

        List<Product> products;
        String productName = name.trim().toLowerCase();

        if (subCategoryId > 0) {
            products = productService.searchProductBySubCategory(subCategoryId, productName);
        } else {
            products = productService.findProductBySearch(productName);
        }

        model.addAttribute("products", products);
        return "frontend/layout/pages/productSearch";
    }

    @RequestMapping(value = "/collections/", method = RequestMethod.GET)
    public String productFilterByPrice(Model model, @RequestParam("from") Integer fromPrice,
                                       @RequestParam("to") Integer toPrice) {

        List<Product> products = productService.filterProductByPrice(fromPrice, toPrice);

        model.addAttribute("products", products);
        return "frontend/layout/pages/productSearch";
    }

}
