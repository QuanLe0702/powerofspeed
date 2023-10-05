package vn.aptech.powerofspeed.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.aptech.powerofspeed.model.user.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}