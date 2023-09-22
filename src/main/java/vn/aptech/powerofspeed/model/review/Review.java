package vn.aptech.powerofspeed.model.review;

import lombok.*;
import vn.aptech.powerofspeed.model.products.Product;
import vn.aptech.powerofspeed.model.user.BaseEntity;
import vn.aptech.powerofspeed.model.user.User;

import javax.persistence.*;

@Entity
@Data
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
