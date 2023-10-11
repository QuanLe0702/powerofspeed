package vn.aptech.powerofspeed.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.aptech.powerofspeed.model.images.Image;
import vn.aptech.powerofspeed.model.products.Product;
import vn.aptech.powerofspeed.model.subcategory.Subcategory;
import vn.aptech.powerofspeed.repository.images.ImagesRepository;
import vn.aptech.powerofspeed.service.ImageService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImagesRepository imgRepository;


    @Override
    public List<Image> findAllImage() {
        List<Image> result = new ArrayList<>();
        Iterable<Image> listImage = imgRepository.findAll();
        for (Image img : listImage) {

            result.add(img);
        }
        return result;
    }

    @Override
    public Image saveImage(Image image) {
        return imgRepository.save(image);

    }


    @Override
    public Optional<Image> findPk(int id) {
        return imgRepository.findById(id);
    }

    @Override
    public void delete(Image image) {
        imgRepository.delete(image);
    }
}
