package vn.aptech.powerofspeed.dto.model.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressDto {


    private Integer addressId;

    private String country;

    private String address;

    private String city;

    private String stateOrRegion;

    private String postalCode;

    public AddressDto() {
    }

    public AddressDto(String country, String address, String city, String stateOrRegion, String postalCode) {
        this.country = country;
        this.address = address;
        this.city = city;
        this.stateOrRegion = stateOrRegion;
        this.postalCode = postalCode;
    }

    public AddressDto(Integer addressId, String country, String address, String city, String stateOrRegion, String postalCode) {
        this.addressId = addressId;
        this.country = country;
        this.address = address;
        this.city = city;
        this.stateOrRegion = stateOrRegion;
        this.postalCode = postalCode;
    }
}

