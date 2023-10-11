package vn.aptech.powerofspeed.controller.v1.ui.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.aptech.powerofspeed.controller.v1.command.UserUpdateFormCommand;
import vn.aptech.powerofspeed.dto.mapper.UserMapper;
import vn.aptech.powerofspeed.dto.model.user.AddressDto;
import vn.aptech.powerofspeed.dto.model.user.UserDto;
import vn.aptech.powerofspeed.model.products.Product;
import vn.aptech.powerofspeed.model.user.User;
import vn.aptech.powerofspeed.model.wishlist.Wishlist;
import vn.aptech.powerofspeed.service.UserService;
import vn.aptech.powerofspeed.service.WishlistService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "account")
@PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF', 'CUSTOMER')")
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private WishlistService wishlistService;

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String viewDashboard(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.findByEmail(authentication.getName());

        model.addAttribute("currentUser", userDto);
        return "frontend/layout/pages/account/dashboard/dashboard";

    }

    @RequestMapping(value = "/wishlist", method = RequestMethod.GET)
    public String myWishList(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getName() == null) {
            return "redirect:/login";
        }

        List<Product> productWishList = new ArrayList<>();
        User user = userService.findUserByEmail(authentication.getName()); // get user by email

        List<Wishlist> wishlist = wishlistService.findAllByUser(user.getId());

        for (Wishlist wish : wishlist) {
            productWishList.add(wish.getProduct());
        }

        model.addAttribute("productWishList", productWishList);
        model.addAttribute("wishlist", wishlist);

        return "frontend/layout/pages/account/wishlist";

    }


    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.findByEmail(authentication.getName());

        model.addAttribute("currentUser", userDto);

        return "frontend/layout/pages/account/profile/profile";
    }

    @RequestMapping(value = "profile/edit", method = RequestMethod.GET)
    public String edit(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.findByEmail(authentication.getName());
        UserUpdateFormCommand userUpdateFormCommand = UserMapper.toUserUpdate(userDto);

        model.addAttribute("userUpdateFormCommand", userUpdateFormCommand);

        return "frontend/layout/pages/account/profile/edit";
    }

    @RequestMapping(value = "profile/edit", method = RequestMethod.POST)
    public String update(@Valid UserUpdateFormCommand userUpdateFormCommand, BindingResult bindingResult) {

        if (!userUpdateFormCommand.getNewPassword().trim().isEmpty() && !userUpdateFormCommand.getConfirmNewPassword().trim().isEmpty()) {
            if (!userService.checkIfValidOldPassword(userUpdateFormCommand)) {
                bindingResult.addError(new ObjectError("password", "Password invalid"));
            }

            if (!userUpdateFormCommand.getNewPassword().equals(userUpdateFormCommand.getConfirmNewPassword())) {
                bindingResult.addError(new ObjectError("newPassword", "New password and confirm password not match"));
            }
        }

        if (bindingResult.hasErrors()) {
            return "frontend/layout/pages/account/profile/edit";
        } else {
            try {
                UserDto updateUser = updateUser(userUpdateFormCommand);
                if (userService.checkIfValidOldPassword(userUpdateFormCommand)) {
                    return "redirect:/logout";
                }
                return "redirect:/account/profile";
            } catch (Exception exception) {
                bindingResult.rejectValue("email", "error.userUpdateFormCommand", exception.getMessage());
                return "frontend/layout/pages/account/profile/edit";
            }
        }
    }

    private UserDto updateUser(UserUpdateFormCommand userUpdateFormCommand) throws IOException {
        UserDto userDto = new UserDto()
                .setId(userUpdateFormCommand.getId())
                .setEmail(userUpdateFormCommand.getEmail())
                .setPassword(userUpdateFormCommand.getConfirmNewPassword())
                .setPhoneNumber(userUpdateFormCommand.getPhoneNumber())
                .setFirstName(userUpdateFormCommand.getFirstName())
                .setLastName(userUpdateFormCommand.getLastName())
                .setGender(userUpdateFormCommand.getGender())
                .setAddressDto(new AddressDto()
                        .setAddress(userUpdateFormCommand.getAddress())
                        .setCity(userUpdateFormCommand.getCity())
                        .setCountry(userUpdateFormCommand.getCountry())
                        .setPostalCode(userUpdateFormCommand.getPostalCode())
                        .setStateOrRegion(userUpdateFormCommand.getStateOrRegion())
                )
                .setDateOfBirth(userUpdateFormCommand.getDateOfBirth());

        return userService.update(userDto, userUpdateFormCommand.getFilePicture());
    }
}
