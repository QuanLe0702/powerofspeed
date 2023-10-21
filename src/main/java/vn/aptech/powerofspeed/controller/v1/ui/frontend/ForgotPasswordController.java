package vn.aptech.powerofspeed.controller.v1.ui.frontend;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vn.aptech.powerofspeed.controller.v1.command.UserRegisterFormCommand;
import vn.aptech.powerofspeed.controller.v1.response.MessageResponse;
import vn.aptech.powerofspeed.model.user.User;
import vn.aptech.powerofspeed.repository.user.UserRepository;
import vn.aptech.powerofspeed.service.EmailService;

@Controller
public class ForgotPasswordController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    PasswordEncoder encoder;

    @RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
    public String forgotPassword(Model model) {

        return "frontend/layout/pages/forgotPassword";
    }

    // Forgot password
    @RequestMapping(value = "/forgotPassword/{email}", method = RequestMethod.POST)
    public ResponseEntity<?> forgotPassword(@PathVariable("email") String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User not found."));
        }
        String newPassword = emailService.randomString();
        user.setPassword(encoder.encode(newPassword));
        userRepository.save(user);
        Map<String, Object> emailMap = new HashMap<>();
        emailMap.put("newPassword", newPassword);
        emailMap.put("name", user.getFirstName() + " " + user.getLastName());
        String templateHtml = emailService.templateResolve("NewPassword", emailMap);
        emailService.sendTemplateMessage(email, null, "New Password", templateHtml);

        return ResponseEntity.ok(new MessageResponse("New password sent to your email!"));
    }

   

    // Change password
    // @PostMapping("/updatePassword/{id}")
    // public ResponseEntity<?> updatePassword(@RequestBody PasswordRequest
    // passwordRequest, @PathVariable Long id) {
    // User user = userRepository.findById(id).orElseThrow(() -> new
    // RuntimeException("Error: User not found."));
    // if (!encoder.matches(passwordRequest.getOldPassword(), user.getPassword())) {
    // return ResponseEntity.ok(new MessageResponse("Old password is not
    // correct!"));
    // }
    // user.setPassword(encoder.encode(passwordRequest.getNewPassword()));
    // userRepository.save(user);
    // return ResponseEntity.ok(new MessageResponse("Password updated
    // successfully!"));
    // }

}
