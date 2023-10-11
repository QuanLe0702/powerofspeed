package vn.aptech.powerofspeed.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.aptech.powerofspeed.dto.mapper.BlogMappper;
import vn.aptech.powerofspeed.dto.mapper.UserMapper;
import vn.aptech.powerofspeed.dto.model.user.blog.BlogDto;
import vn.aptech.powerofspeed.model.blog.Blog;
import vn.aptech.powerofspeed.model.user.User;
import vn.aptech.powerofspeed.repository.blog.BlogRepository;
import vn.aptech.powerofspeed.repository.user.UserRepository;
import vn.aptech.powerofspeed.service.BlogService;
import vn.aptech.powerofspeed.util.FileUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    private final String BLOG_IMAGE_PATH = "backend/dist/img/blog";

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public List<BlogDto> findAll() {
        List<BlogDto> result = new ArrayList<>();
        List<Blog> blogs = blogRepository.findAll();

        for (Blog blog : blogs) {
            BlogDto blogDto = new BlogDto();
            blogDto.setId(blog.getId());
            blogDto.setTitle(blog.getTitle());
            blogDto.setDescription(blog.getDescription());
            blogDto.setImage(blog.getImage());
            blogDto.setCreateAt(blog.getCreateAt());
            result.add(blogDto);
        }
        return result;
    }

    @Override
    public BlogDto create(BlogDto blogDto) throws IOException {
        Blog blog = new Blog();

        String uniqueFileName = FileUtil.UploadedFile(blogDto.getMultipartFile(), BLOG_IMAGE_PATH);
        blog.setTitle(blogDto.getTitle());
        blog.setDescription(blogDto.getDescription());
        blog.setImage(uniqueFileName);

        return BlogMappper.toBlogDto(blogRepository.save(blog));
    }

    @Override
    public Blog update(BlogDto blogDto) throws IOException {
        Blog blog = new Blog();



        String uniqueFileName = FileUtil.UploadedFile(blogDto.getMultipartFile(),BLOG_IMAGE_PATH);
        blog.setId(blogDto.getId());
        blog.setTitle(blogDto.getTitle());
        blog.setDescription((blogDto.getDescription()));
        blog.setImage(uniqueFileName);

        return blogRepository.save(blog);
    }

    @Override
    public BlogDto findById(long id) throws IOException {
        try {
            Blog blog = blogRepository.findById(id).get();
            BlogDto blogDto = new BlogDto();
            return BlogMappper.toBlogDto(blogRepository.save(blog));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(long id) {
   Blog blog = blogRepository.findById((id)).get();
   BlogDto blogDto = new BlogDto();
   blogRepository.delete(blog);
    }


}
