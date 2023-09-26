package vn.aptech.powerofspeed.dto.mapper;
import org.springframework.stereotype.Component;

import vn.aptech.powerofspeed.dto.model.user.contactUs.ContactUsDto;
import vn.aptech.powerofspeed.model.contactus.ContactUs;

@Component
public class ContactUsMapper {

    public static ContactUsDto toContactUsDto(ContactUs contactUs){
        return new ContactUsDto()
                .setId(contactUs.getId())
                .setName(contactUs.getName())
                .setEmail(contactUs.getEmail())
                .setSubject(contactUs.getSubject())
                .setPhone(contactUs.getPhone())
                 .setMessage(contactUs.getMessage())
                .setCreateAt(contactUs.getCreateAt());
    }
}
