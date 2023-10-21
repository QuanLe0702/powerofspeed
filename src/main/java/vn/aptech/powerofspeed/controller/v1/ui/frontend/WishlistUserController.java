package vn.aptech.powerofspeed.controller.v1.ui.frontend;

import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.aptech.powerofspeed.dto.model.user.UserDto;
import vn.aptech.powerofspeed.model.cart.Cart;
import vn.aptech.powerofspeed.model.products.Product;
import vn.aptech.powerofspeed.model.user.User;
import vn.aptech.powerofspeed.model.wishlist.Wishlist;
import vn.aptech.powerofspeed.service.ProductService;
import vn.aptech.powerofspeed.service.UserService;
import vn.aptech.powerofspeed.service.WishlistService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "wishlist")
@PreAuthorize("hasAnyAuthority('CUSTOMER')")
public class WishlistUserController {

    @Autowired
    ProductService productService;

    @Autowired
    WishlistService wishlistService;

    @Autowired
    UserService userService;

     @RequestMapping(value = "/add/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> add(HttpServletRequest httpServletRequest, @PathVariable("id") Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
        User user = userService.findUserByEmail(authentication.getName()); // get user by email
        Wishlist wishlistCurrent = wishlistService.checkExists(id, user.getId());
        if (wishlistCurrent != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Wishlist already exists");
        } else {
            Product product = productService.findPk(id);

            Wishlist wishlist = new Wishlist();
            wishlist.setProduct(product);
            wishlist.setUser(user);
            wishlistService.create(wishlist);
            // boolean created = wishlistService.create(wishlist);
            // if (!created) {
            //     return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create wishlist");
            // }
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
