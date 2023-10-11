package vn.aptech.powerofspeed.repository.wishlist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import vn.aptech.powerofspeed.model.wishlist.Wishlist;

import java.util.List;
import java.util.Optional;

public interface WishlistRepository extends JpaRepository<Wishlist,Long> {

    @Query(value = "select * from wishlist wl where wl.user_id = :userId", nativeQuery = true)
    List<Wishlist> findAllByUserId(Long userId);

    @Query(value = "select * from wishlist wl where wl.product_id = :productId and wl.user_id = :userId", nativeQuery = true)
    Optional<Wishlist> findByProductId(Long productId, Long userId);
}
