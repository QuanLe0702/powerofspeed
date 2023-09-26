package vn.aptech.powerofspeed.controller.v1.ui.frontend;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.aptech.powerofspeed.repository.blog.BlogRepository;
import vn.aptech.powerofspeed.service.BlogService;

@Controller

public class BlogUserController {
    @Autowired
    private BlogService blogService;

    @Autowired
    BlogRepository blogRepository;

    @RequestMapping(value="/blog")
    public String blog(Model model){
        model.addAttribute("blogs",blogService.findAll());
        return "frontend/layout/pages/blog";
    }

    @RequestMapping(value = "/blog/{id}", method = RequestMethod.GET)
    public String blog(Model model, @PathVariable("id") Long id) throws IOException {
        model.addAttribute("blogs",blogService.findById(id));

        return "frontend/layout/pages/blogDetail";
    }
}
