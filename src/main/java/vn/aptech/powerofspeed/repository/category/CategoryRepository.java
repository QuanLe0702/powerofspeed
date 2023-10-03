package vn.aptech.powerofspeed.repository.category;

import org.springframework.data.repository.CrudRepository;
import vn.aptech.powerofspeed.model.category.Category;

public interface CategoryRepository extends CrudRepository<Category,Integer> {

    Category findBySlug(String slug);
}
