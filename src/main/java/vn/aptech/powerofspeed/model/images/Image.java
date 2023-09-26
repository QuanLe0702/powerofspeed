package vn.aptech.powerofspeed.model.images;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.aptech.powerofspeed.model.products.Product;
import vn.aptech.powerofspeed.model.user.User;


@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "images")
public class Image implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "image_id")
    private Integer imageId;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Column(name = "blog_id")
    private Integer blogId;
    @Column(name = "about_id")
    private Integer aboutId;
    @Column(name = "main_image")
    public boolean mainImage;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;

    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Product product;

    public Image() {
    }

    public Image(Integer imageId) {
        this.imageId = imageId;
    }

    public Image(Integer imageId, String name, String description) {
        this.imageId = imageId;
        this.name = name;
        this.description = description;
    }

}

