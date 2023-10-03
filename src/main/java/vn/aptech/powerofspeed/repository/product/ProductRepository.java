package vn.aptech.powerofspeed.repository.product;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.aptech.powerofspeed.model.products.Product;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findBySlug(String slug);
}
