package vn.aptech.powerofspeed.controller.v1.ui.backend;


import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
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
import vn.aptech.powerofspeed.dto.model.user.about.AboutDto;
import vn.aptech.powerofspeed.dto.model.user.blog.BlogDto;
import vn.aptech.powerofspeed.model.about.About;
import vn.aptech.powerofspeed.service.AboutService;
import vn.aptech.powerofspeed.service.UserService;

import javax.validation.Valid;
import java.io.IOException;
@Controller
@RequestMapping(value="admin/about")
public class AboutController {

    @Autowired
    private AboutService aboutService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"", "/", "index"}, method = RequestMethod.GET)
    public String about(Model model) {
        model.addAttribute("abouts", aboutService.findAll());
        return "backend/layout/pages/about/index";
    }

    //GET
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("aboutDto", new AboutDto());
        return "backend/layout/pages/about/create";
    }

    //POST
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("aboutDto") AboutDto aboutDto, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return "backend/layout/pages/about/create";
        } else {
            try {
//                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//                if (authentication != null) {
//                    UserDto user = userService.findByEmail(authentication.getName());
//                    aboutDto.setUserDto(user);
//                }
                AboutDto dto = aboutService.create(aboutDto);
                return "redirect:/admin/about";
            } catch (Exception exception) {
                bindingResult.rejectValue("id", "error.aboutDto", exception.getMessage());
                return "backend/layout/pages/about/create";
            }

        }
    }
    //UPDATE-GET
    @RequestMapping(value="/displayUpdate/{idUpdate}", method = RequestMethod.GET)
    public String displayUpdate(Model model, @PathVariable("idUpdate")Long id) throws IOException{
        AboutDto aboutDto = aboutService.findById(id);
        if(aboutDto!=null){
            model.addAttribute("aboutDto",aboutDto);
        }
        return "backend/layout/pages/about/update";
    }
    //UPDATE-POST
    @RequestMapping(value="doUpdate",method=RequestMethod.POST)
    public String doUpdate(Model model,
                           @ModelAttribute("abouts")AboutDto aboutDto) throws IOException {
        aboutService.update(aboutDto);
        return "redirect:/admin/about";
    }
    //DELETE
    @RequestMapping(value="/delete/{idDelete}", method=RequestMethod.GET)
    public String deleteAbout(RedirectAttributes redirectAttributes, @PathVariable("idDelete")String id){
        AboutDto aboutDto = aboutService.findById(Long.parseLong(id));
        aboutService.delete(Long.parseLong(id));
        redirectAttributes.addFlashAttribute("success","Delete about"+aboutDto.getName()+"Successfully");
        return "redirect:/admin/about";
    }

}