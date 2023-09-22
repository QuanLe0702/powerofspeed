package vn.aptech.powerofspeed.model.inventory;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import vn.aptech.powerofspeed.model.products.Product;
import vn.aptech.powerofspeed.model.subcategory.Subcategory;
import vn.aptech.powerofspeed.model.supplier.Supplier;
import vn.aptech.powerofspeed.model.user.BaseEntity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "inventory")
public class Inventory  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "inventory_ID")
    private Integer inventoryID;


    @Column(name = "starting_inventory")
    private Integer startingInventory;

    @Column(name = "quantity_received")
    private Integer quantityReceived;

    @Column(name = "inventory_on_hand")
    private Integer inventoryOnHand;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Product product;



    @Basic(optional = false)
    @Column(name = "purchase_date")
    private Date purchaseDate;

    public Inventory() {
    }

    public Inventory(Integer inventoryID, Integer startingInventory, Integer quantityReceived, Integer inventoryOnHand, Supplier supplier, Product product, Date purchaseDate) {
        this.inventoryID = inventoryID;
        this.startingInventory = startingInventory;
        this.quantityReceived = quantityReceived;
        this.inventoryOnHand = inventoryOnHand;
        this.supplier = supplier;
        this.product = product;
        this.purchaseDate = purchaseDate;
    }
}

