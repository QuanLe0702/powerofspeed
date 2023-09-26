package vn.aptech.powerofspeed.service;

import java.io.IOException;
import java.util.List;

import vn.aptech.powerofspeed.dto.model.user.blog.BlogDto;
import vn.aptech.powerofspeed.model.blog.Blog;

public interface BlogService {

    List<BlogDto> findAll();
    BlogDto create(BlogDto blogDto) throws IOException;
    Blog update(BlogDto blogDto) throws  IOException;
    BlogDto findById(long id) throws IOException;
    void delete(long id);
}
