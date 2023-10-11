package vn.aptech.powerofspeed.model.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.aptech.powerofspeed.model.products.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<CartItem> items;
    private int total;

    // khoi tao
    public Cart() {
        items = new ArrayList<CartItem>(); // khoi tao cac item
        total = 0; // gan tong = 0
    }

    // phuong thuc lay item
    public CartItem getItem(Product p) {
        for (CartItem item : items) {
            if (item.getProduct().getId() == p.getId()) {// da ton tai san pham trong gio hang
                return item;
            }
        }
        return null; // khong ton tai san pham trong gio hang
    }

    // phuong thuc lay ve tat ca cac item
    public List<CartItem> getItems() {
        return items;
    }

    // lay ve so luong cac item
    public int getItemCount() {
        return items.size();
    }

    // them mot item
    public void addItem(CartItem item) {// ham goi ham duoi
        addItem(item.getProduct(), item.getQuantity());
    }

    // them item voi so luong cho truoc
    public void addItem(Product p, int quantity) {
        CartItem item = getItem(p);
        if (item != null) {
            item.setQuantity(item.getQuantity() + quantity); // so luong cu + so luong moi
        } else {// neu chua ton tai san pham
            item = new CartItem(p); // tao moi mot san pham
            item.setQuantity(quantity); // set so luong
            items.add(item); // them vao list cac item
        }
    }

    // update san pham
    public void updateItem(Product p, int quantity) {
        CartItem item = getItem(p); // lay ve san pham can update
        if (item != null) {
            item.setQuantity(quantity);
        }
    }

    // xoa san pham
    public void removeItem(Product p) {
        CartItem item = getItem(p);
        if (item != null) {
            items.remove(item);
        }
    }

    // xoa toan bo gio hang
    public void clearAllItem() {
        items.clear(); // xoa toan bo gio hang
        total = 0; // gan tong = 0
    }

    // kiem tra gio hang co trong hay khong
    public boolean isEmpty() {
        return items.isEmpty();
    }

    // lay ve tong tien
    public Integer getTotal() {
        total = 0;
        for (CartItem item : items) {
            total += item.getSubTotal();
        }
        return total;
    }
}
