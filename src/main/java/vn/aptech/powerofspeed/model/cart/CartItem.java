package vn.aptech.powerofspeed.model.cart;

import vn.aptech.powerofspeed.model.products.Product;

public class CartItem {
    private Product product;
    private int quantity;
    private int subTotal;

    public CartItem() {

    }

    public CartItem(Product product) {
        this.product = product;
        this.quantity = 1; // khoi tao so luong
        this.subTotal = product.getSavePrice(); // tinh tien cho 1 san pham
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSubTotal() {
        subTotal = product.getSavePrice() * quantity;
        return subTotal;
    }

    public void setSubTotal(int subTotal) {
        this.subTotal = subTotal;
    }
}
