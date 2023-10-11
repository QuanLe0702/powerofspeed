package vn.aptech.powerofspeed.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import vn.aptech.powerofspeed.controller.v1.command.UserUpdateFormCommand;
import vn.aptech.powerofspeed.dto.model.user.UserDto;
import vn.aptech.powerofspeed.model.user.User;

public interface UserService {
    UserDto findByEmail(String email);

    UserDto findById(Long id);

    List<UserDto> findAll();

    boolean checkIfValidOldPassword(UserUpdateFormCommand userUpdateFormCommand);

    UserDto register(UserDto userDto) throws IOException;

    UserDto update(UserDto userDto, MultipartFile filePicture) throws IOException;

    void delete(Long id);

    User findUserByEmail(String email);

}
