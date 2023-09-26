package vn.aptech.powerofspeed.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.aptech.powerofspeed.model.user.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
