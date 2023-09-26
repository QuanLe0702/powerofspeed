package vn.aptech.powerofspeed.repository.about;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.aptech.powerofspeed.model.about.About;

@Repository
public interface AboutRepository extends JpaRepository<About,Long> {
}
