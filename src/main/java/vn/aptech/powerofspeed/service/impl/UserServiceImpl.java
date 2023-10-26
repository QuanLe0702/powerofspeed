package vn.aptech.powerofspeed.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import vn.aptech.powerofspeed.controller.v1.command.UserUpdateFormCommand;
import vn.aptech.powerofspeed.dto.mapper.UserMapper;
import vn.aptech.powerofspeed.dto.model.user.UserDto;
import vn.aptech.powerofspeed.exception.ApplicationException;
import vn.aptech.powerofspeed.exception.EntityType;
import vn.aptech.powerofspeed.exception.ExceptionType;
import vn.aptech.powerofspeed.model.user.Address;
import vn.aptech.powerofspeed.model.user.Role;
import vn.aptech.powerofspeed.model.user.User;
import vn.aptech.powerofspeed.repository.user.AddressRepository;
import vn.aptech.powerofspeed.repository.user.RoleRepository;
import vn.aptech.powerofspeed.repository.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import vn.aptech.powerofspeed.service.EmailService;
import vn.aptech.powerofspeed.service.UserService;
import vn.aptech.powerofspeed.util.FileUtil;

import java.io.IOException;
import java.sql.Date;
import java.util.*;

import static vn.aptech.powerofspeed.exception.EntityType.USER;
import static vn.aptech.powerofspeed.exception.ExceptionType.DUPLICATE_ENTITY;
import static vn.aptech.powerofspeed.exception.ExceptionType.ENTITY_NOT_FOUND;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    private final String PROFILE_IMAGE_PATH = "backend/dist/img/user-picture";

    @Autowired
    private EmailService emailService;

    @Transactional
    public UserDto findByEmail(String email) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        if (user.isPresent()) {
            return UserMapper.toUserDto(user.get());
        }
        throw exception(USER, ENTITY_NOT_FOUND, email);
    }

    @Override
    public UserDto findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return UserMapper.toUserDto(user.get());
        }
        throw exception(USER, ENTITY_NOT_FOUND, id.toString());
    }

    @Override
    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        users.forEach(user -> userDtos.add(UserMapper.toUserDto(user)));

        return userDtos;

    }

    @Override
    public boolean checkIfValidOldPassword(UserUpdateFormCommand userUpdateFormCommand) {
        User currentUser = userRepository.findByEmail(userUpdateFormCommand.getEmail());

        return passwordEncoder.matches(userUpdateFormCommand.getPassword(), currentUser.getPassword());
    }

    @Override
    public UserDto register(UserDto userDto) throws IOException {
        Set<Role> roles = new HashSet<>();
        User user = userRepository.findByEmail(userDto.getEmail());
        UserDto returnUser = null;

        if (userDto.isAdmin()) {
            try {
                String uniqueFileName = FileUtil.UploadedFile(userDto.getMultipartFile(), PROFILE_IMAGE_PATH);

                if (user == null) {
                    for (String roleId : userDto.getRoles()) {
                    roles.add(roleRepository.findById(Long.parseLong(roleId)).get());
                    }
                    // Role role = roleRepository.findByName("STAFF");
                    // roles.add(role);
                    user = new User()
                            .setEmail(userDto.getEmail())
                            .setEmailConfirmed(true)
                            .setPassword(passwordEncoder.encode(userDto.getPassword()))
                            .setPhoneNumber(userDto.getPhoneNumber())
                            .setPhoneConfirmed(true)
                            .setFirstName(userDto.getFirstName())
                            .setLastName(userDto.getLastName())
                            .setGender(userDto.getGender())
                            .setDateOfBirth(userDto.getDateOfBirth())
                            .setProfilePicture(uniqueFileName)
                            .setAddress(addressRepository.save(new Address()))
                            .setRoles(roles);

                    returnUser = UserMapper.toUserDto(userRepository.save(user));

                    // send email to user staff
                    emailService.sendHTMLemail(userDto.getFirstName(), userDto.getEmail());

                    return returnUser;
                }
                throw exception(USER, DUPLICATE_ENTITY, userDto.getEmail());
            } catch (IOException ioException) {
                throw new RuntimeException(ioException.getMessage());
            }
        } else {
            if (user == null) {
                Role role = roleRepository.findByName("CUSTOMER");
                roles.add(role);
                user = new User()
                        .setEmail(userDto.getEmail())
                        .setEmailConfirmed(true)
                        .setPassword(passwordEncoder.encode(userDto.getPassword()))
                        .setPhoneNumber(userDto.getPhoneNumber())
                        .setPhoneConfirmed(true)
                        .setFirstName(userDto.getFirstName())
                        .setLastName(userDto.getLastName())
                        .setGender(User.GenderType.Other)
                        .setDateOfBirth(Date.valueOf("1999-07-26"))
                        .setAddress(addressRepository.save(new Address()))
                        .setRoles(roles);

                returnUser = UserMapper.toUserDto(userRepository.save(user));

                // send email to user with token
                emailService.sendHTMLemail(userDto.getFirstName(), userDto.getEmail());

                return returnUser;
            }
            throw exception(USER, DUPLICATE_ENTITY, userDto.getEmail());
        }
    }

    @Override
    public UserDto update(UserDto userDto, MultipartFile filePicture) throws IOException {
        User user = userRepository.findByEmail(userDto.getEmail());
        UserDto returnUser = null;
        String uniqueFileName = null;

        if (user != null) {
            if (userDto.isAdmin()) {
                User userUpdate = user;
                String originPicture = userUpdate.getProfilePicture();
                if (!(userDto.getPassword().isEmpty())) {
                    userUpdate.setPassword(passwordEncoder.encode(userDto.getPassword()));
                }
                if (!filePicture.isEmpty()) {
                    uniqueFileName = FileUtil.UploadedFile(filePicture, PROFILE_IMAGE_PATH);
                    if (uniqueFileName != null) {
                        userUpdate.setProfilePicture(uniqueFileName);
                        FileUtil.DeletedFile(PROFILE_IMAGE_PATH, originPicture);
                    }
                }
                userUpdate.setPhoneNumber(userDto.getPhoneNumber());
                userUpdate.setFirstName(userDto.getFirstName());
                userUpdate.setLastName(userDto.getLastName());
                userUpdate.setGender(userDto.getGender());
                userUpdate.setDateOfBirth(userDto.getDateOfBirth());

                returnUser = UserMapper.toUserDto(userRepository.save(userUpdate));
                return returnUser;
            } else {
                User userUpdate = user;
                String originPicture = userUpdate.getProfilePicture();
                if (!(userDto.getPassword().isEmpty())) {
                    userUpdate.setPassword(passwordEncoder.encode(userDto.getPassword()));
                }
                if (!filePicture.isEmpty()) {
                    uniqueFileName = FileUtil.UploadedFile(filePicture, PROFILE_IMAGE_PATH);
                    if (uniqueFileName != null) {
                        userUpdate.setProfilePicture(uniqueFileName);
                        FileUtil.DeletedFile(PROFILE_IMAGE_PATH, originPicture);
                    }
                }
                userUpdate.setPhoneNumber(userDto.getPhoneNumber());
                userUpdate.setFirstName(userDto.getFirstName());
                userUpdate.setLastName(userDto.getLastName());
                userUpdate.setGender(userDto.getGender());
                userUpdate.setDateOfBirth(userDto.getDateOfBirth());

                Optional<Address> address = addressRepository.findById(userUpdate.getAddress().getId());
                Address addressUpdate = address.get();
                addressUpdate.setAddress(userDto.getAddressDto().getAddress());
                addressUpdate.setCity(userDto.getAddressDto().getCity());
                addressUpdate.setCountry(userDto.getAddressDto().getCountry());
                addressUpdate.setPostalCode(userDto.getAddressDto().getPostalCode());
                addressUpdate.setStateOrRegion(userDto.getAddressDto().getStateOrRegion());
                userUpdate.setAddress(addressUpdate);

                returnUser = UserMapper.toUserDto(userRepository.save(userUpdate));
                return returnUser;
            }
        }
        throw exception(USER, ENTITY_NOT_FOUND, userDto.getId().toString());
    }

    @Override
    public void delete(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
        } else {
            throw exception(USER, ENTITY_NOT_FOUND, id.toString());
        }
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return ApplicationException.throwException(entityType, exceptionType, args);
    }
}
