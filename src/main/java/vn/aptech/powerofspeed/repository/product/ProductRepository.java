package vn.aptech.powerofspeed.repository.product;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.aptech.powerofspeed.model.products.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findBySlug(String slug);

    Product findBySku(String sku);

    @Query(value = "select * from products p where p.product_name like '%' :name '%'", nativeQuery = true)
    List<Product> productSearchList(String name);

    @Query(value = "select * from products p where  p.subcategory_id = :subCategoryId && p.product_name like '%' :name '%'", nativeQuery = true)
    List<Product> searchProductBySubCategory(Long subCategoryId, String name);

    @Query(value = "select * from products p where p.unit_price between :from and :to", nativeQuery = true)
    List<Product> filterProductByPrice(Integer from, Integer to);

    @Query(value = "select * from products p order by p.unit_price asc", nativeQuery = true)
    Page<Product> sortProductByAsc(Pageable pageable);

    @Query(value = "select * from products p order by p.unit_price desc", nativeQuery = true)
    Page<Product> sortProductByDesc(Pageable pageable);

    @Query(value = "select * from products p order by p.create_at desc ", nativeQuery = true)
    Page<Product> sortNewProduct(Pageable pageable);

    @Query(value = "select * from products p order by p.save_price asc", nativeQuery = true)
    Page<Product> sortProductByMuchDiscount(Pageable pageable);

    @Query(value = "select * from products p where p.save_price", nativeQuery = true)
    List<Product> findAllBySalePrice();




}
