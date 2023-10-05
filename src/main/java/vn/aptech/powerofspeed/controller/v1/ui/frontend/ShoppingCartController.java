package vn.aptech.powerofspeed.controller.v1.ui.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.aptech.powerofspeed.model.cart.Cart;
import vn.aptech.powerofspeed.model.products.Product;
import vn.aptech.powerofspeed.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "cart")
public class ShoppingCartController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = {"/",""},method = RequestMethod.GET)
    public String cart(ModelMap mm, HttpSession session){
        HashMap<Long, Cart> cartItems = (HashMap<Long, Cart>) session.getAttribute("myCartItems");
        if(cartItems == null){
            cartItems = new HashMap<>();
        }
        session.setAttribute("myCartItems", cartItems);
        session.setAttribute("myCartTotal", totalPrice(cartItems));
        session.setAttribute("myCartNum", cartItems.size());
        return "frontend/layout/pages/cart";
    }

    @RequestMapping(value = "add/{productId}", method = RequestMethod.GET)
    public String viewAdd(ModelMap mm, HttpSession session, @PathVariable("productId") Long productId){
        HashMap<Long, Cart> cartItems = (HashMap<Long, Cart>) session.getAttribute("myCartItems");
        if(cartItems == null){
            cartItems = new HashMap<>();
        }
        Product product = productService.findPk(productId);
        if(product != null){
            if(cartItems.containsKey(productId)){
                Cart item = cartItems.get(productId);
                item.setProduct(product);
                item.setQuantity(item.getQuantity() + 1);
                cartItems.put(productId, item);
            }else {
                Cart item = new Cart();
                item.setProduct(product);
                item.setQuantity(1);
                cartItems.put(productId,item);
            }
        }
        session.setAttribute("myCartItems", cartItems);
        session.setAttribute("myCartTotal", totalPrice(cartItems));
        session.setAttribute("myCartNum", cartItems.size());
        return "frontend/layout/pages/cart";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String viewUpdate(ModelMap mm, HttpSession session, HttpServletRequest request){
        HashMap<Long,Cart> cartItems = (HashMap<Long,Cart>) session.getAttribute("myCartItems");
        if(cartItems == null){
            cartItems = new HashMap<>();
        }
        String[] quantity = request.getParameterValues("quantity");
        for (int i=0; i<cartItems.size();i++){
            cartItems.get(i).setQuantity(Integer.parseInt(quantity[i]));
        }
        session.setAttribute("myCartItems", cartItems);
        return "frontend/layout/pages/cart";
    }

    @RequestMapping(value = "remove/{productId}", method = RequestMethod.GET)
    public String viewRemove(ModelMap mm, HttpSession session, @PathVariable("productId") Long productId){
        HashMap<Long,Cart> cartItems = (HashMap<Long, Cart>) session.getAttribute("myCartItems");
        if(cartItems == null){
            cartItems = new HashMap<>();
        }
        if(cartItems.containsKey(productId)){
            cartItems.remove(productId);
        }
        session.setAttribute("myCartItems", cartItems);
        session.setAttribute("myCartTotal", totalPrice(cartItems));
        session.setAttribute("myCartNum", cartItems.size());
        return "frontend/layout/pages/cart";
    }

    public double totalPrice(HashMap<Long, Cart> cartItems){
        int count = 0;
        for(Map.Entry<Long,Cart> list: cartItems.entrySet()){
            count += list.getValue().getProduct().getSavePrice()*list.getValue().getQuantity();
        }
        return count;
    }

}
