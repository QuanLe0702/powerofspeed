package vn.aptech.powerofspeed.controller.v1.ui.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.aptech.powerofspeed.controller.v1.request.BidAuctionRequest;
import vn.aptech.powerofspeed.controller.v1.request.ReviewRequest;
import vn.aptech.powerofspeed.model.category.Category;
import vn.aptech.powerofspeed.model.products.Product;
import vn.aptech.powerofspeed.model.review.Review;
import vn.aptech.powerofspeed.model.subcategory.Subcategory;
import vn.aptech.powerofspeed.model.user.User;
import vn.aptech.powerofspeed.repository.OrderDetailRepository;
import vn.aptech.powerofspeed.repository.review.ReviewRepository;
import vn.aptech.powerofspeed.repository.user.UserRepository;
import vn.aptech.powerofspeed.service.CategoryService;
import vn.aptech.powerofspeed.service.ProductService;
import vn.aptech.powerofspeed.service.SubcategoryService;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ShopProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SubcategoryService subcategoryService;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @RequestMapping(value = "product/{slug}", method = RequestMethod.GET)
    public String productDetail(Model model, @PathVariable("slug") String slug) {

        Product product = productService.findBySlug(slug);

        if (product == null) {
            return "redirect:/accessDenied";
        }

        int hot = product.getHot() + 1;
        product.setHot(hot);

        product = productService.update(product);

        model.addAttribute("productDetail", product);

        List<Review> review = reviewRepository.findByProductId(product.getId());
        model.addAttribute("review", review);

        if (product.getBidDetail() != null) {
            BidAuctionRequest bidAuctionRequest = new BidAuctionRequest();
            int bidAmout = product.getBidDetail().getCurrentPrice() + product.getBidDetail().getBidIncrement();
            bidAuctionRequest.setBidAmout(bidAmout);
            bidAuctionRequest.setId(product.getId());
            model.addAttribute("bidAuctionRequest", bidAuctionRequest);
            return "frontend/layout/pages/bidDetail";
        }

        return "frontend/layout/pages/productDetail";
    }

    @RequestMapping(value = "/reviews", method = RequestMethod.POST)
    public ResponseEntity<String> createReview(@RequestBody ReviewRequest reviewRequest, @RequestParam Long userId,
            @RequestParam Long productId) {
        // Kiểm tra xem người dùng có tồn tại không
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            System.out.println("userId: " + userId);
            // throw new IllegalArgumentException("Người dùng không tồn tại.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Người dùng không tồn tại.");
        }

        // Kiểm tra xem sản phẩm có tồn tại không
        Product product = productService.findPk(productId);
        if (product == null) {
            System.out.println("productId: " + productId);
            throw new IllegalArgumentException("Sản phẩm không tồn tại.");
        }

        // Kiểm tra xem người dùng đã đặt hàng sản phẩm hay chưa
        boolean hasOrdered = orderDetailRepository.existsByUserIdAndProductId(userId, productId);
        if (!hasOrdered) {
            // throw new IllegalArgumentException("Người dùng chưa đặt hàng sản phẩm.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sản phẩm không tồn tại.");
        }

        // Tạo đánh giá
        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setContent(reviewRequest.getContent());
        review.setRating(reviewRequest.getRating());
        reviewRepository.save(review);

        return ResponseEntity.ok("Đánh giá đã được tạo thành công.");
        // return "redirect:/product/{productId}";
    }

    @RequestMapping(value = "/collection/{categorySlug}", method = RequestMethod.GET)
    public String getProductByCategories(Model model, @PathVariable("categorySlug") String categorySlug) {

        List<Product> products = new ArrayList<>();
        Category category = categoryService.findBySlug(categorySlug);
        List<Category> categories = categoryService.findAllCat();
        ;

        if (category == null || categories.size() == 0) {
            return "redirect:/accessDenied";
        }

        for (Subcategory subCategory : category.getSubcategories()) {
            for (Product product : subCategory.getProducts()) {
                products.add(product);
            }
        }

        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        model.addAttribute("category", category);

        return "frontend/layout/pages/productCategoryList";

    }

    @RequestMapping(value = "/collection/{categorySlug}/{subCategorySlug}", method = RequestMethod.GET)
    public String getProductBySubCategories(Model model, @PathVariable("categorySlug") String categorySlug,
            @PathVariable("subCategorySlug") String subCategorySlug) {
        Subcategory subcategory = subcategoryService.findBySlug(subCategorySlug);
        List<Category> categories = categoryService.findAllCat();

        if (subcategory == null || categories.size() == 0) {
            return "redirect:/accessDenied";
        }

        model.addAttribute("categories", categories);
        model.addAttribute("subcategory", subcategory);

        return "frontend/layout/pages/productSubCategoryList";

    }

    @RequestMapping(value = "/collections", method = RequestMethod.GET)
    public String getProductCollections(Model model,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "size", required = false) String size,
            @RequestParam(value = "order", required = false) String orderby) {

        int pageDefault = 1;
        int sizeDefault = 6;

        if (page != null && !page.isEmpty()) {
            pageDefault = Integer.parseInt(page);
        }

        if (size != null && !size.isEmpty()) {
            sizeDefault = Integer.parseInt(size);
        }

        PageRequest pageRequest = PageRequest.of(pageDefault - 1, sizeDefault);
        Page<Product> products = null;

        if (orderby != null && !orderby.isEmpty()) {
            switch (orderby) {
                case "new_product": {
                    products = productService.sortNewProduct(pageRequest);
                }
                    break;
                case "much_discount": {

                    products = productService.sortProductByMuchDiscount(pageRequest);
                }
                    break;
                case "price_low_to_heigh": {

                    products = productService.sortProductByPriceAsc(pageRequest);
                }
                    break;
                case "price_heigh_to_low": {

                    products = productService.sortProductByPriceDesc(pageRequest);

                }
                    break;
            }
        } else {
            products = productService.findAllByPaging(pageRequest);
        }

        List<Category> categories = categoryService.findAllCat();
        ;

        int totalPages = products.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("categories", categories);
        model.addAttribute("products", products);

        return "frontend/layout/pages/productCollections";
    }

    @RequestMapping(value = "/auction-products", method = RequestMethod.GET)
    public String getAuctionProducts(Model model) {
        List<Product> auctionProducts = new ArrayList<>();
        List<Product> products = productService.findAllPro();
        List<Category> categories = categoryService.findAllCat();
        ;

        if (products.size() == 0 || categories.size() == 0) {
            return "redirect:/accessDenied";
        }

        for (Product product : products) {
            if (product.getBidDetail() != null && product.isStatus()) {
                auctionProducts.add(product);
            }
        }

        model.addAttribute("categories", categories);
        model.addAttribute("auctionProducts", auctionProducts);

        return "frontend/layout/pages/auctionProduct";
    }
}
