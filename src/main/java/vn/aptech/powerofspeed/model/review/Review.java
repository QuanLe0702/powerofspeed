package vn.aptech.powerofspeed.model.review;

import lombok.*;
import  vn.aptech.powerofspeed.model.products.Product;
import  vn.aptech.powerofspeed.model.user.BaseEntity;
import  vn.aptech.powerofspeed.model.user.User;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "review")
public class Review  {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
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
