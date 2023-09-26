package vn.aptech.powerofspeed.dto.model.user.about;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

@Getter
@Setter
@Accessors(chain = true)
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AboutDto {
    private long id;
    private String name;
    private String description;
    private Timestamp createAt;
    private MultipartFile multipartFile;
    private String image;


}
