package vn.aptech.powerofspeed.controller.v1.ui.backend;


import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.aptech.powerofspeed.dto.model.user.about.AboutDto;
import vn.aptech.powerofspeed.service.AboutService;
import vn.aptech.powerofspeed.service.UserService;

@Controller
@RequestMapping(value="admin/about")
public class AboutController {

    @Autowired
    private AboutService aboutService;

    @Autowired
    private UserService userService;

    @RequestMapping(value={"","/","index"}, method= RequestMethod.GET)
    public String about(Model model){
        model.addAttribute("abouts",aboutService.findAll());
        return "backend/layout/pages/about/index";
    }
    //GET
    @RequestMapping(value="/create",method=RequestMethod.GET)
    public String create(Model model){
        model.addAttribute("aboutDto",new AboutDto());
        return "backend/layout/pages/about/create";
    }
    @RequestMapping(value="create",method=RequestMethod.POST)
    public String create(@Valid @ModelAttribute("aboutDto")AboutDto aboutDto, BindingResult bindingResult) throws IOException {
//        if (bindingResult.hasErrors()) {
//            return "backend/layout/pages/blog/create";
//        } else {
//            try {
//                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//                if (authentication != null) {
//                    UserDto user = userService.findByEmail(authentication.getName());
//                    aboutDto.setUserDto(user);
//                }
//                BlogDto blog = blogService.create(blogDto);
//                return "redirect:/admin/blog";
//            } catch (Exception exception) {
//                bindingResult.rejectValue("id", "error.blogDto", exception.getMessage());
               AboutDto dto = aboutService.create(aboutDto);
                return "backend/layout/pages/about/create";
            }

        }

