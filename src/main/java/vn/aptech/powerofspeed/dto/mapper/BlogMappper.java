package vn.aptech.powerofspeed.dto.mapper;

import org.springframework.stereotype.Component;

import vn.aptech.powerofspeed.dto.model.user.blog.BlogDto;
import vn.aptech.powerofspeed.model.blog.Blog;

@Component
public class BlogMappper {

    public static BlogDto toBlogDto(Blog blog) {
        return new BlogDto()
                .setId(blog.getId())
                .setTitle(blog.getTitle())
                .setImage(blog.getImage())
                .setDescription(blog.getDescription())
                .setCreateAt(blog.getCreateAt());
    }
}
