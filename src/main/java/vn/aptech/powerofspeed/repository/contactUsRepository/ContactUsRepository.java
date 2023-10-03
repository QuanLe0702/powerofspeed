package vn.aptech.powerofspeed.repository.contactUsRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.aptech.powerofspeed.model.contactus.ContactUs;

@Repository
public interface ContactUsRepository extends JpaRepository<ContactUs, Long> {
}
