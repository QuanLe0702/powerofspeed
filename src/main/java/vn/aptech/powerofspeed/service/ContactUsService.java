package vn.aptech.powerofspeed.service;

import vn.aptech.powerofspeed.dto.model.user.contactUs.ContactUsDto;
import vn.aptech.powerofspeed.model.contactus.ContactUs;

import java.util.List;
import java.util.Optional;

public interface ContactUsService {
    List<ContactUsDto> findAll();
    ContactUsDto create(ContactUsDto contactUsDto);
    Optional<ContactUs> findById(long id);

}
