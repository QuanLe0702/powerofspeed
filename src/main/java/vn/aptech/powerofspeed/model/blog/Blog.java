package vn.aptech.powerofspeed.model.blog;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import vn.aptech.powerofspeed.model.user.BaseEntity;
import vn.aptech.powerofspeed.model.user.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "blogs")
public class Blog extends BaseEntity{


    @Column(name="title")
    private String title;

    @Column(name="description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "user_id") // thông qua khóa ngoại address_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;



}






























