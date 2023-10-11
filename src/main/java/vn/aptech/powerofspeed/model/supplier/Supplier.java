package vn.aptech.powerofspeed.model.supplier;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import vn.aptech.powerofspeed.model.inventory.Inventory;
import vn.aptech.powerofspeed.model.products.Product;
import vn.aptech.powerofspeed.model.user.BaseEntity;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "suppliers")
public class Supplier extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Inventory> inventory;

    public Supplier() {
    }

    public Supplier(String name, String telephone, String address, Collection<Inventory> inventory) {
        this.name = name;
        this.telephone = telephone;
        this.address = address;
        this.inventory = inventory;
    }
}
