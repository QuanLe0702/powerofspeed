package vn.aptech.powerofspeed.repository.images;

import org.springframework.data.repository.CrudRepository;
import vn.aptech.powerofspeed.model.images.Image;
import vn.aptech.powerofspeed.model.products.Product;

public interface ImagesRepository extends CrudRepository<Image,Integer> {
}
