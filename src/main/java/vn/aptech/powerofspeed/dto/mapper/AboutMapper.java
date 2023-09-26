package vn.aptech.powerofspeed.dto.mapper;
import org.springframework.stereotype.Component;

import vn.aptech.powerofspeed.dto.model.user.about.AboutDto;
import vn.aptech.powerofspeed.model.about.About;

@Component
public class AboutMapper {

    public static AboutDto toAboutDto(About about){
        return new AboutDto()
                .setId(about.getId())
                .setName(about.getName())
                .setDescription(about.getDescription())
                .setCreateAt(about.getCreateAt()) 
                .setImage(about.getImage());
    }
}
