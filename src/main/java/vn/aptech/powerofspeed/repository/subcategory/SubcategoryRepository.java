package vn.aptech.powerofspeed.repository.subcategory;

import org.springframework.data.repository.CrudRepository;
import vn.aptech.powerofspeed.model.subcategory.Subcategory;

public interface SubcategoryRepository extends CrudRepository<Subcategory,Integer> {

    Subcategory findBySlug(String slug);
}
