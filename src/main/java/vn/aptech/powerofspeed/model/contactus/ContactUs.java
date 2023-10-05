package vn.aptech.powerofspeed.model.contactus;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.aptech.powerofspeed.model.user.BaseEntity;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name="contactUs")

public class ContactUs extends BaseEntity {

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="subject")
    private String subject;

    @Column(name="phone")
    private String phone;

    @Column(name="message")
    private String message;

}
