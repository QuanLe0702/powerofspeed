package  vn.aptech.powerofspeed.model.cart;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class CartManager {
    private static final String SESSION_KEY_SHOPPING_CART="gioHang";
    //phuong thuc lay ve gio hang
    public Cart getCart(HttpSession session){
        Cart cart = (Cart)session.getAttribute(SESSION_KEY_SHOPPING_CART);
        if(cart==null){
            cart = new Cart();
            setCart(session, cart);
        }
        return cart;
    }
    //thiet lap mot gio hang
    public void setCart(HttpSession session, Cart cart){
        session.setAttribute(SESSION_KEY_SHOPPING_CART, cart);
    }

    //xoa mot gio hang
    public void removeCart(HttpSession session){
        session.removeAttribute(SESSION_KEY_SHOPPING_CART);
    }
}
