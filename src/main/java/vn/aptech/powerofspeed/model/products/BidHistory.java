package vn.aptech.powerofspeed.model.products;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import vn.aptech.powerofspeed.model.user.BaseEntity;
import vn.aptech.powerofspeed.model.user.User;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "bid_history")
public class BidHistory extends BaseEntity {

    @OneToOne()
    @JoinColumn(name = "user_id")
    private User bidder;

    @ManyToOne
    @JoinColumn(name = "bid_detail_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private BidDetail bidDetail;

    @Column(name = "unit_price")
    private int unitPrice;
}
