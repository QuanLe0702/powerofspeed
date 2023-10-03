package vn.aptech.powerofspeed.service;

import vn.aptech.powerofspeed.model.images.Image;


import java.util.List;
import java.util.Optional;

public interface ImageService {

    List<Image> findAllImage();
    Image saveImage(Image image);
    Optional<Image> findPk(int id);
    void delete(int id);
}
