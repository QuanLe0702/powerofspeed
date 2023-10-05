package vn.aptech.powerofspeed.controller.v1.ui.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.aptech.powerofspeed.controller.v1.request.BidAuctionRequest;
import vn.aptech.powerofspeed.model.category.Category;
import vn.aptech.powerofspeed.model.products.Product;
import vn.aptech.powerofspeed.model.subcategory.Subcategory;
import vn.aptech.powerofspeed.service.CategoryService;
import vn.aptech.powerofspeed.service.ProductService;
import vn.aptech.powerofspeed.service.SubcategoryService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ShopProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SubcategoryService subcategoryService;

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

    @RequestMapping(value = "/collection/{categorySlug}", method = RequestMethod.GET)
    public String getProductByCategories(Model model, @PathVariable("categorySlug") String categorySlug) {

        List<Product> products = new ArrayList<>();
        Category category = categoryService.findBySlug(categorySlug);
        List<Category> categories = categoryService.findAllCat();;

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
    public String getProductBySubCategories(Model model, @PathVariable("categorySlug") String categorySlug, @PathVariable("subCategorySlug") String subCategorySlug) {
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
    public String getProductCollections(Model model) {

        List<Product> products = productService.findAllPro();
        List<Category> categories = categoryService.findAllCat();;

        if (products.size() == 0) {
            return "redirect:/accessDenied";
        }

        model.addAttribute("categories", categories);
        model.addAttribute("products", products);

        return "frontend/layout/pages/productCollections";
    }

    @RequestMapping(value = "/auction-products", method = RequestMethod.GET)
    public String getAuctionProducts(Model model) {
        List<Product> auctionProducts = new ArrayList<>();
        List<Product> products = productService.findAllPro();
        List<Category> categories = categoryService.findAllCat();;

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
