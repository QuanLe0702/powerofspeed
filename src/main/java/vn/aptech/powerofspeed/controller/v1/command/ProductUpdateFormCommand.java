package vn.aptech.powerofspeed.controller.v1.command;

import lombok.Data;
import lombok.experimental.Accessors;
import vn.aptech.powerofspeed.model.products.ProductType;
import vn.aptech.powerofspeed.model.subcategory.Subcategory;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.sql.Date;
import java.util.List;

@Data
@Accessors(chain = true)
public class ProductUpdateFormCommand {

    private Long id;

    @NotNull
    private String slug;

    @NotNull
    private String sku;

    @NotNull
    private String productName;

    private String productDescription;

    private String productContent;

    @NotNull
    // @Size(min = 1)
    private int unitPrice;

    @Size(min = 1)
    private int savePrice;

    private double unitWeight;

    @NotNull
    private int stock;

    private ProductType productType;

    private boolean status;

    private int bidIncrement;

    private Date auctionStart;

    private Date auctionEnd;

    private boolean isAuction = false;

    private int category;

    List<Subcategory> subcategories;
}
