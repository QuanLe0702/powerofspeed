package vn.aptech.powerofspeed.model.contactus;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import vn.aptech.powerofspeed.model.user.BaseEntity;
import vn.aptech.powerofspeed.model.user.User;

import javax.persistence.*;

@Getter
@Setter
@Accessors
@Entity
@Table(name = "contactUs")
public class ContactUs extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "subject")
    private String subject;

    @Column(name = "phone")
    private String phone;

    @Column(name = "message")
    private String message;

}
