package vn.aptech.powerofspeed.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.aptech.powerofspeed.model.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
