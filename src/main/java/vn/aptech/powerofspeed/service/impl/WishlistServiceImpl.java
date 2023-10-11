package vn.aptech.powerofspeed.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.aptech.powerofspeed.model.category.Category;
import vn.aptech.powerofspeed.model.wishlist.Wishlist;
import vn.aptech.powerofspeed.repository.category.CategoryRepository;
import vn.aptech.powerofspeed.repository.wishlist.WishlistRepository;
import vn.aptech.powerofspeed.service.WishlistService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WishlistServiceImpl implements WishlistService {

    @Autowired
    WishlistRepository wishlistRepository;


    @Override
    public List<Wishlist> findAllByUser(Long userId) {
        return wishlistRepository.findAllByUserId(userId);
    }

    @Override
    public Wishlist create(Wishlist wishlist) {
        return wishlistRepository.save(wishlist);
    }

    @Override
    public void update(Wishlist wishlist) {
        wishlistRepository.save(wishlist);
    }



    @Override
    public Wishlist findPk(Long id) {
        try {
            Wishlist wishlist = wishlistRepository.findById(id).get();

            return wishlist;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Wishlist checkExists(Long productId, Long userId) {
        Wishlist wishlist = wishlistRepository.findByProductId(productId, userId).orElse(null);
        return wishlist;
    }

    @Override
    public void delete(Long id) {
        Wishlist wishlist = wishlistRepository.findById(id).get();
        wishlistRepository.delete(wishlist);
    }
}
