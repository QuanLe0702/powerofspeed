package vn.aptech.powerofspeed.controller.v1.command;

import java.sql.Date;
import java.util.List;

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
public class UserRegisterFormCommand {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 5)
    private String password;

    @NotBlank
    @Size(min = 5)
    @FieldMatch(first = "password", second = "confirmPassword", message = "Passowords are not equal.")
    private String passwordConfirm;

    @NotBlank
    @Size(min = 5, max = 13)
    private String phoneNumber;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private User.GenderType gender;

    public Date dateOfBirth;

    private MultipartFile profilePicture;

    private List<String> roles;

    public enum GenderType {
        Male,
        Female,
        Other
    }

}
