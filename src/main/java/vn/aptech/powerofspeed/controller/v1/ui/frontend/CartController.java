package vn.aptech.powerofspeed.controller.v1.ui.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.aptech.powerofspeed.model.cart.Cart;
import vn.aptech.powerofspeed.model.cart.CartManager;
import vn.aptech.powerofspeed.model.products.Product;
import vn.aptech.powerofspeed.service.ProductService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("cart")
public class CartController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CartManager cartManager;

    @RequestMapping(value = {"/",""}, method = RequestMethod.GET)
    public String cart(){
        return "frontend/layout/pages/cart";
    }

    @RequestMapping(value = "/add/{id}", method = RequestMethod.GET) // them san pham vao gio hang
    public String add(HttpSession session, @PathVariable("id") Long id, @RequestParam(value = "qty", required = false, defaultValue = "1") int qty, RedirectAttributes redirectAttributes){
        Product product = productService.findPk(id); // lay ve san pham

        if (product.getStock() < qty) {
            redirectAttributes.addFlashAttribute("outofstock", "Quantity can not be greater than the stock!");
            return "redirect:/product/" + product.getSlug();
        }
        Cart cart = cartManager.getCart(session); //lay ve gio hang
        cart.addItem(product,qty); // them san pham vao gio hang
        return "frontend/layout/pages/cart";
    }

    @RequestMapping(value = "/addMulti", method = RequestMethod.POST) // them nhieu san pham vao gio hang
    public String addMultiQuantity(HttpSession session, @RequestParam("id") Long id, @RequestParam(value = "qty") int qty, RedirectAttributes redirectAttributes){
        Product product = productService.findPk(id); // lay ve san pham
        if (product.getStock() < qty) {
            redirectAttributes.addFlashAttribute("outofstock", "Quantity can not be greater than the stock!");
            return "redirect:/product/" + product.getSlug();
        }
        Cart cart = cartManager.getCart(session); //lay ve gio hang
        cart.addItem(product,qty); // them san pham vao gio hang
        return "frontend/layout/pages/cart";
    }

    @RequestMapping(value = "/remove/{id}") // xoa gio hang
    public String remove(HttpSession session, @PathVariable("id") Long id){
        Product product = productService.findPk(id); //lay ve san pham
        Cart cart = cartManager.getCart(session); //lay ve gio hang
        cart.removeItem(product);
        return "frontend/layout/pages/cart";
    }
    @RequestMapping(value = "/update", method = RequestMethod.POST) //sua san pham trong gio hang
    public String update(HttpSession session, @RequestParam("id") Long id, @RequestParam("qty") int qty, RedirectAttributes redirectAttributes){
        Product product = productService.findPk(id); //lay ve san pham
        Cart cart = cartManager.getCart(session); // lay ve gio hang
        if (product.getStock() < qty) {
            redirectAttributes.addFlashAttribute("outofstock", "Quantity can not be greater than the stock!");
            return "redirect:/cart";
        }
        cart.updateItem(product,qty);
        return "frontend/layout/pages/cart";
    }
}
