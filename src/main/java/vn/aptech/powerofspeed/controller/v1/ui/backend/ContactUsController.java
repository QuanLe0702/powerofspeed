package vn.aptech.powerofspeed.controller.v1.ui.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.aptech.powerofspeed.dto.model.user.contactUs.ContactUsDto;
import vn.aptech.powerofspeed.service.ContactUsService;
import vn.aptech.powerofspeed.service.UserService;

@Controller
@RequestMapping(value ="admin/contactUs")
public class ContactUsController {

    @Autowired
    private UserService userService;

    @Autowired
    private ContactUsService contactUsService;

    @RequestMapping(value={"/","","index"},method= RequestMethod.GET)
    public String contactUs(Model model){
        model.addAttribute("contactUses",contactUsService.findAll());
        return "backend/layout/pages/contactUs/index";
    }
    //GET
    @RequestMapping(value="/create",method=RequestMethod.GET)
    public String create(Model model){
        model.addAttribute("contactUsDto",new ContactUsDto());
        return "backend/layout/pages/contactUs/create";

    }

}
