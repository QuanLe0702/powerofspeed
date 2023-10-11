package vn.aptech.powerofspeed.model.products;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import vn.aptech.powerofspeed.model.subcategory.Subcategory;
import vn.aptech.powerofspeed.model.user.Address;
import vn.aptech.powerofspeed.model.user.BaseEntity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "bid_details")
public class BidDetail extends BaseEntity {

    @Column(name = "current_price")
    private int currentPrice;

    @Column(name = "bid_increment")
    private int bidIncrement;

    @Column(name = "auction_start")
    private Date auctionStart;

    @Column(name = "auction_end")
    private Date auctionEnd;

    @OneToMany(mappedBy = "bidDetail", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<BidHistory> bidHistories;
}
