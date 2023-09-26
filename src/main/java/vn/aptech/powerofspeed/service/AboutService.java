package vn.aptech.powerofspeed.service;

import java.io.IOException;
import java.util.List;

import vn.aptech.powerofspeed.dto.model.user.about.AboutDto;
import vn.aptech.powerofspeed.model.about.About;

public interface AboutService {
    List<AboutDto> findAll();
    AboutDto findById(long id);
    AboutDto create(AboutDto aboutDto) throws IOException;
    About update(AboutDto aboutDto) throws  IOException;
    void delete(long id);

}
