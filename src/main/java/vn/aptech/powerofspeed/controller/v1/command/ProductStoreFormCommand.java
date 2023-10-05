package vn.aptech.powerofspeed.controller.v1.command;

import lombok.Data;
import lombok.experimental.Accessors;
import vn.aptech.powerofspeed.model.category.Category;
import vn.aptech.powerofspeed.model.products.Product;
import vn.aptech.powerofspeed.model.products.ProductType;
import vn.aptech.powerofspeed.model.subcategory.Subcategory;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

@Data
@Accessors(chain = true)
public class ProductStoreFormCommand {

    @NotNull
    private String slug;

    @NotNull
    private String sku;

    @NotNull
    private String productName;

    private String productDescription;

    private String productContent;

    @NotNull
    private int unitPrice;

    @NotNull
    private int savePrice;

    private double unitWeight;

    @NotNull
    private int stock;

    private ProductType productType;

    private boolean status;

    private int bidIncrement;

    private Date auctionStart;

    private Date auctionEnd;

    private String category;

    List<Subcategory> subcategories;
}
