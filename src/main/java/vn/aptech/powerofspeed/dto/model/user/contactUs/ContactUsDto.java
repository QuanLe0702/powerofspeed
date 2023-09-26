package vn.aptech.powerofspeed.dto.model.user.contactUs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;
import vn.aptech.powerofspeed.dto.model.user.UserDto;

import java.sql.Timestamp;

@Getter
@Setter
@Accessors(chain = true)
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactUsDto {
    private long id;
    private String name;
    private String subject;
    private String email;
    private String phone;
    private String message;
    private Timestamp createAt;
    private UserDto userDto;
    private MultipartFile multipartFile;
}
