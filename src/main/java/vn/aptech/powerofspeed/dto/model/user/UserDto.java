package vn.aptech.powerofspeed.dto.model.user;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import vn.aptech.powerofspeed.model.user.User;

@Getter
@Setter
@Accessors(chain = true)
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    private Long id;
    private String email;
    private boolean emailConfirmed;
    private String password;
    private String phoneNumber;
    private boolean phoneConfirmed;
    private String firstName;
    private String lastName;
    private User.GenderType gender;
    private Date dateOfBirth;
    private String profilePicture;
    private boolean status = false;
    private Timestamp createAt;
    private Timestamp updateAt;
    private AddressDto addressDto;
    private List<String> roles;
    private Set<RoleDto> roleDtos;
    private MultipartFile multipartFile;
    private boolean isAdmin;


    public UserDto() {
    }

    public UserDto(String email, boolean emailConfirmed, String password, String phoneNumber, boolean phoneConfirmed, String firstName, String lastName, User.GenderType gender, Date dateOfBirth, String profilePicture, boolean status, AddressDto addressDto, Set<RoleDto> roleDtos) {
        this.email = email;
        this.emailConfirmed = emailConfirmed;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.phoneConfirmed = phoneConfirmed;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.profilePicture = profilePicture;
        this.status = status;
        this.addressDto = addressDto;
        this.roleDtos = roleDtos;
    }

    public UserDto(Long id, String email, boolean emailConfirmed, String password, String phoneNumber, boolean phoneConfirmed, String firstName, String lastName, User.GenderType gender, Date dateOfBirth, String profilePicture, boolean status, AddressDto addressDto, Set<RoleDto> roleDtos) {
        this.id = id;
        this.email = email;
        this.emailConfirmed = emailConfirmed;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.phoneConfirmed = phoneConfirmed;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.profilePicture = profilePicture;
        this.status = status;
        this.addressDto = addressDto;
        this.roleDtos = roleDtos;
    }

    public String getFullName() {
        return firstName != null ? firstName.concat(" ").concat(lastName) : "";
    }
}