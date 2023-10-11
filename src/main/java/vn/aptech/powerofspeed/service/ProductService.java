package vn.aptech.powerofspeed.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.aptech.powerofspeed.model.category.Category;
import vn.aptech.powerofspeed.model.products.BidHistory;
import vn.aptech.powerofspeed.model.products.Product;

import java.util.List;

public interface ProductService {

    Page<Product> findAllByPaging(Pageable pageable);
    List<Product> findAllPro();
    List<Product> findAllBySalePrice();
    Product create(Product pro);
    Product update(Product pro);
    Product findPk(Long id);
    Product findBySlug(String productSlug);
    Product findBySKU(String sku);
    void delete(Long id);
    Product bidAuction(Product product);
    BidHistory storeBidHistory(BidHistory bidHistory);
    List<Product> findProductBySearch(String name);
    List<Product> filterProductByPrice(Integer from, Integer to);
    List<Product> searchProductBySubCategory(Long subCategoryId, String searchProductName);
    Page<Product> sortProductByPriceAsc(Pageable pageable);
    Page<Product> sortProductByPriceDesc(Pageable pageable);
    Page<Product> sortNewProduct(Pageable pageable);
    Page<Product> sortProductByMuchDiscount(Pageable pageable);
}