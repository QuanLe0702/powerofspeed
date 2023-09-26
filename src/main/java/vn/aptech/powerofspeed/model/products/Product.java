package vn.aptech.powerofspeed.model.products;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import vn.aptech.powerofspeed.model.images.Image;
import vn.aptech.powerofspeed.model.inventory.Inventory;
import vn.aptech.powerofspeed.model.orderdetail.OrderDetail;
import vn.aptech.powerofspeed.model.review.Review;
import vn.aptech.powerofspeed.model.subcategory.Subcategory;
import vn.aptech.powerofspeed.model.user.BaseEntity;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Column(name = "slug")
    private String slug;

    @Column(name = "sku")
    private String sku;

    @Column(name = "product_name", nullable = false, unique = true)
    private String productName;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "product_content", columnDefinition = "TEXT")
    private String productContent;

    @Column(name = "unit_price", nullable = false)
    private int unitPrice;

    @Column(name = "save_price", nullable = false)
    private int savePrice;

    @Column(name = "brand")
    private String brand;

    @Column(name = "stock", nullable = false)
    private int stock;

    @Column(name = "product_type")
    private ProductType productType;

    @Column(name = "hot")
    private int hot;

    @OneToOne()
    @JoinColumn(name = "bid_detail_id")
    public BidDetail bidDetail;

    @ManyToOne
    @JoinColumn(name = "subcategory_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Subcategory subcategory;


    @Column(name = "inventory_shipped")
    private Integer inventoryShipped;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Collection<Image> imagesCollection;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Review> reviewsCollection;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Inventory> inventory;
}
