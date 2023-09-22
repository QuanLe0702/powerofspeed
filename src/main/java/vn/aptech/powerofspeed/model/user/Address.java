package vn.aptech.powerofspeed.model.user;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "addresses")
public class Address extends BaseEntity {

    @Column(name = "country")
    private String country;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "state_region")
    private String stateOrRegion;

    @Column(name = "postal_code")
    private String postalCode;

    public Address() {
    }

    public Address(String country, String address, String city, String stateOrRegion, String postalCode) {
        this.country = country;
        this.address = address;
        this.city = city;
        this.stateOrRegion = stateOrRegion;
        this.postalCode = postalCode;
    }
}
