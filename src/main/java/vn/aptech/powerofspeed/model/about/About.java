package vn.aptech.powerofspeed.model.about;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.aptech.powerofspeed.model.user.BaseEntity;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name="abouts")
public class About extends BaseEntity {
    @Column(name="name")
    private String name;
    @Column(name="description")
    private String description;
    @Column(name="image")
    private String image;

}
