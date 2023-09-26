package vn.aptech.powerofspeed.model.review;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import vn.aptech.powerofspeed.model.products.Product;
import vn.aptech.powerofspeed.model.user.BaseEntity;
import vn.aptech.powerofspeed.model.user.User;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "review")
public class Review extends BaseEntity {

    @Column(name = "content")
    private String content;

    @Column(name = "rating")
    private float rating;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;
}
