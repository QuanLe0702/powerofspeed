package vn.aptech.powerofspeed.controller.v1.ui.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.aptech.powerofspeed.dto.model.user.UserDto;
import vn.aptech.powerofspeed.dto.model.user.blog.BlogDto;
import vn.aptech.powerofspeed.model.blog.Blog;
import vn.aptech.powerofspeed.model.images.Image;
import vn.aptech.powerofspeed.model.user.User;
import vn.aptech.powerofspeed.service.BlogService;
import vn.aptech.powerofspeed.service.UserService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping(value = "admin/blog")
@PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @RequestMapping(value={"/","","index"}, method = RequestMethod.GET)
    public String blog(Model model){
        model.addAttribute("blogs", blogService.findAll());
        return "backend/layout/pages/blog/index";
    }
//GET
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("blogDto", new BlogDto());

        return "backend/layout/pages/blog/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("blogDto")BlogDto blogDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "backend/layout/pages/blog/create";
        } else {
            try {
                BlogDto blog = blogService.create(blogDto);
                return "redirect:/admin/blog";
            } catch (Exception exception) {
                bindingResult.rejectValue("id", "error.blogDto", exception.getMessage());
                return "backend/layout/pages/blog/create";
            }
        }

    }
    //UPDATE - GET
    @RequestMapping(value="displayUpdate/{idUpdate}")
    public String displayUpdate(Model model, @PathVariable("idUpdate") String id) throws IOException {
        BlogDto blogDto = blogService.findById(Integer.parseInt(id));
        if(blogDto!=null){
            model.addAttribute("blogDto",blogDto);
        }
        return "backend/layout/pages/blog/update";
    }
    //UPDATE - POST
    @RequestMapping(value="/doUpdate", method=RequestMethod.POST)
    public String doUpdate(Model model,
                           @ModelAttribute("blogs")BlogDto blogDto) throws IOException {
        blogService.update(blogDto);
        return "redirect:/admin/blog";
    }
    //DELETE
    @RequestMapping(value="/delete/{idDelete}", method=RequestMethod.GET)
    public String deleteBlog(RedirectAttributes redirectAttributes, @PathVariable("idDelete") String id) throws IOException {
        BlogDto blogDto = blogService.findById(Long.parseLong(id));
        blogService.delete(Long.parseLong(id));
        redirectAttributes.addFlashAttribute("success","Delete blog"+blogDto.getTitle()+"Successfully");
        return "redirect:/admin/blog";
    }

}
