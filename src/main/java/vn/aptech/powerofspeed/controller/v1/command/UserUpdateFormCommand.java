package vn.aptech.powerofspeed.controller.v1.command;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.experimental.Accessors;
import vn.aptech.powerofspeed.annotation.FieldMatch;
import vn.aptech.powerofspeed.model.user.User;

@Data
@Accessors(chain = true)
public class UserUpdateFormCommand {

    private Long id;

    @NotBlank
    @Email
    private String email;

    // @NotBlank
    // @Size(min = 5)
    private String password;

    // @NotBlank
    // @Size(min = 5)
    private String newPassword;

    // @NotBlank
    // @Size(min = 5)
    // @FieldMatch(first = "newPassword", second = "confirmNewPassword", message = "Passwords are not equal.")
    private String confirmNewPassword;

    @NotBlank
    @Size(min = 5, max = 13)
    private String phoneNumber;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private User.GenderType gender;

    public Date dateOfBirth;

    private String profilePicture;

    private MultipartFile filePicture;

    @NotBlank
    private String country;

    @NotBlank
    private String address;

    @NotBlank
    private String city;

    @NotBlank
    private String stateOrRegion;

    @NotBlank
    private String postalCode;

    private boolean isAdmin;

    public String getFullName() {
        return firstName != null ? firstName.concat(" ").concat(lastName) : "";
    }

    public enum GenderType {
        Male,
        Female
    }
}