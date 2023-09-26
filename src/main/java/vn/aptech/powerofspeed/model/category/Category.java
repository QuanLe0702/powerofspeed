package vn.aptech.powerofspeed.model.category;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import vn.aptech.powerofspeed.model.subcategory.Subcategory;


@Getter
@Setter
@Entity
@Table(name = "categories")
@Accessors(chain = true)
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "category_ID")
    private Integer categoryID;

    @Column(name = "slug", nullable = false, unique = true)
    private String slug;

    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Subcategory> subcategories;
}

