package vn.aptech.powerofspeed.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.aptech.powerofspeed.dto.mapper.ContactUsMapper;
import vn.aptech.powerofspeed.dto.model.user.contactUs.ContactUsDto;
import vn.aptech.powerofspeed.model.contactus.ContactUs;
import vn.aptech.powerofspeed.model.user.User;
import vn.aptech.powerofspeed.repository.contactUsRepository.ContactUsRepository;
import vn.aptech.powerofspeed.repository.user.UserRepository;
import vn.aptech.powerofspeed.service.ContactUsService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactUsServiceImpl implements ContactUsService {
    @Autowired
    private ContactUsRepository contactUsRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<ContactUsDto> findAll() {
        List<ContactUsDto> result = new ArrayList<>();
        List<ContactUs> contactUses = contactUsRepository.findAll();
        for (ContactUs contactUs : contactUses) {
            ContactUsDto contactUsDto = new ContactUsDto();
            contactUsDto.setId(contactUs.getId());
            contactUsDto.setCreateAt(contactUs.getCreateAt());
            contactUsDto.setEmail(contactUs.getEmail());
            contactUsDto.setMessage(contactUs.getMessage());
            contactUsDto.setName(contactUs.getName());
            contactUsDto.setPhone(contactUs.getPhone());
            contactUsDto.setMessage(contactUs.getMessage());
            result.add(contactUsDto);
        }
        return result;
    }

    @Override
    public ContactUsDto create(ContactUsDto contactUsDto) {
        ContactUs contactUs = new ContactUs();

        contactUs.setName(contactUsDto.getName());
        contactUs.setEmail(contactUsDto.getEmail());
        contactUs.setPhone(contactUsDto.getPhone());
        contactUs.setSubject(contactUsDto.getSubject());
        contactUs.setMessage(contactUsDto.getMessage());
        return ContactUsMapper.toContactUsDto(contactUsRepository.save(contactUs));
    }
}

