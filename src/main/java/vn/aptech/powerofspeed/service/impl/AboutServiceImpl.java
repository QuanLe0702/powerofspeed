package vn.aptech.powerofspeed.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.aptech.powerofspeed.dto.mapper.AboutMapper;
import vn.aptech.powerofspeed.dto.model.user.about.AboutDto;
import vn.aptech.powerofspeed.model.about.About;
import vn.aptech.powerofspeed.repository.about.AboutRepository;
import vn.aptech.powerofspeed.repository.user.UserRepository;
import vn.aptech.powerofspeed.service.AboutService;
import vn.aptech.powerofspeed.util.FileUtil;

@Service
public class AboutServiceImpl implements AboutService {
    private final String ABOUT_IMAGE_PATH="backend/dist/img/about";

    @Autowired
    private AboutRepository aboutRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<AboutDto> findAll() {
        List<AboutDto> result = new ArrayList<>();
        List<About> abouts = aboutRepository.findAll();

        for (About about : abouts){
            AboutDto aboutDto = new AboutDto();
            aboutDto.setId(about.getId());
            aboutDto.setName(about.getName());
            aboutDto.setDescription(about.getDescription());
            aboutDto.setCreateAt(about.getCreateAt());
            aboutDto.setImage(about.getImage());
            result.add(aboutDto);
        }
        return result;
    }

    @Override
    public AboutDto findById(long id) {
        return null;
    }

    @Override
    public AboutDto create(AboutDto aboutDto) throws IOException {
       About about = new About();
       String uniqueFileName = FileUtil.UploadedFile(aboutDto.getMultipartFile(),ABOUT_IMAGE_PATH);
       about.setName((about.getName()));
       about.setDescription(about.getDescription());
       about.setCreateAt(about.getCreateAt());
       about.setImage(uniqueFileName);
       return AboutMapper.toAboutDto(aboutRepository.save(about));
    }

    @Override
    public About update(AboutDto aboutDto) throws IOException {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}



























