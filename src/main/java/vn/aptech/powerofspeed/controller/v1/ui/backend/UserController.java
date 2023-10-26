package vn.aptech.powerofspeed.controller.v1.ui.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.aptech.powerofspeed.controller.v1.command.UserRegisterFormCommand;
import vn.aptech.powerofspeed.controller.v1.command.UserUpdateFormCommand;
import vn.aptech.powerofspeed.dto.mapper.UserMapper;
import vn.aptech.powerofspeed.dto.model.user.RoleDto;
import vn.aptech.powerofspeed.dto.model.user.UserDto;
import vn.aptech.powerofspeed.service.RoleService;
import vn.aptech.powerofspeed.service.UserService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "admin/user")
@PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = {"", "/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        List<UserDto> users = userService.findAll();
        model.addAttribute("users", users);
        return "backend/layout/pages/user/index";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        List<RoleDto> roleDtos = roleService.findAll();
        UserRegisterFormCommand userRegisterFormCommand = new UserRegisterFormCommand();
        model.addAttribute("userRegisterFormCommand", userRegisterFormCommand);
        model.addAttribute("roleDtos", roleDtos);

        return "backend/layout/pages/user/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("userRegisterFormCommand") UserRegisterFormCommand userRegisterFormCommand,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "backend/layout/pages/user/create";
        } else {
            try {
                UserDto newUser = registerUser(userRegisterFormCommand);
                return "redirect:/admin/user";
            } catch (Exception exception) {
                bindingResult.rejectValue("email", "error.userRegisterFormCommand", exception.getMessage());
                return "backend/layout/pages/user/create";
            }
        }
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(Model model , @PathVariable("id") Long id) {
        UserDto userDto = userService.findById(id);
        UserUpdateFormCommand userUpdateFormCommand = UserMapper.toUserUpdate(userDto);

        model.addAttribute("userUpdateFormCommand", userUpdateFormCommand);
        return "backend/layout/pages/user/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("userUpdateFormCommand") UserUpdateFormCommand userUpdateFormCommand,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "backend/layout/pages/user/edit";
        } else {
            try {
                UserDto updateUser = updateUser(userUpdateFormCommand);
                return "redirect:/admin/user";
            } catch (Exception exception) {
                bindingResult.rejectValue("id", "error.userUpdateFormCommand", exception.getMessage());
                return "backend/layout/pages/user/edit";
            }
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(RedirectAttributes redirectAttributes, @PathVariable("id") String id) {
        try {
            userService.delete(Long.parseLong(id));
            redirectAttributes.addFlashAttribute("success", "Delete user with id " + id + " successfully!");
        } catch (Exception exception) {
            redirectAttributes.addFlashAttribute("error", exception.getMessage());
        }
        return "redirect:/admin/user";
    }

    private UserDto registerUser(@Valid UserRegisterFormCommand userRegisterFormCommand) throws IOException {
        UserDto userDto = new UserDto()
                .setEmail(userRegisterFormCommand.getEmail())
                .setPassword(userRegisterFormCommand.getPassword())
                .setFirstName(userRegisterFormCommand.getFirstName())
                .setLastName(userRegisterFormCommand.getLastName())
                .setPhoneNumber(userRegisterFormCommand.getPhoneNumber())
                .setGender(userRegisterFormCommand.getGender())
                .setDateOfBirth(userRegisterFormCommand.getDateOfBirth())
                .setRoles(userRegisterFormCommand.getRoles())
                .setMultipartFile(userRegisterFormCommand.getProfilePicture())
                .setAdmin(true);

        UserDto user = userService.register(userDto);

        return user;
    }

    private UserDto updateUser(@Valid UserUpdateFormCommand userUpdateFormCommand) throws IOException {
        UserDto userDto = new UserDto()
                .setId(userUpdateFormCommand.getId())
                .setEmail(userUpdateFormCommand.getEmail())
                .setPassword(userUpdateFormCommand.getPassword())
                .setPhoneNumber(userUpdateFormCommand.getPhoneNumber())
                .setFirstName(userUpdateFormCommand.getFirstName())
                .setLastName(userUpdateFormCommand.getLastName())
                .setGender(userUpdateFormCommand.getGender())
                .setDateOfBirth(userUpdateFormCommand.getDateOfBirth())
                .setAdmin(true);

        return userService.update(userDto, userUpdateFormCommand.getFilePicture());
    }

}
