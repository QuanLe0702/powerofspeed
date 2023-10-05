package vn.aptech.powerofspeed.controller.v1.ui.frontend;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.aptech.powerofspeed.controller.v1.request.BidAuctionRequest;
import vn.aptech.powerofspeed.dto.model.user.UserDto;
import vn.aptech.powerofspeed.model.products.BidHistory;
import vn.aptech.powerofspeed.model.products.Product;
import vn.aptech.powerofspeed.model.user.User;
import vn.aptech.powerofspeed.service.ProductService;
import vn.aptech.powerofspeed.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AuctionController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/auction/bid", method = RequestMethod.POST)
    public String bidAuction(@Valid @ModelAttribute("bidAuctionRequest") BidAuctionRequest bidAuctionRequest, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDto userDto = userService.findByEmail(authentication.getName());
        Product product = productService.findPk(bidAuctionRequest.getId());


        Integer bidIncrement = product.getBidDetail().getCurrentPrice() + product.getBidDetail().getBidIncrement();
        if (bidAuctionRequest.getBidAmout() <= bidIncrement) {
            redirectAttributes.addFlashAttribute("bidAmoutError", "The bid amount cannot be less than the current price and bid increment combined.");
            return "redirect:/product/" + product.getSlug();
        }

        if (bindingResult.hasErrors()) {
            return "frontend/layout/pages/bidDetail";
        } else {
            List<BidHistory> bidHistories = new ArrayList<>();
            bidHistories.add(productService.storeBidHistory(new BidHistory()
                    .setBidder(new ModelMapper().map(userDto, User.class))
                    .setUnitPrice(bidAuctionRequest.getBidAmout())
                    .setBidDetail(product.getBidDetail())
            ));

            product.getBidDetail().setBidHistories(bidHistories);
            product.getBidDetail().setCurrentPrice(bidAuctionRequest.getBidAmout());

            productService.update(product);

            return "redirect:/product/" + product.getSlug();
        }

    }
}
