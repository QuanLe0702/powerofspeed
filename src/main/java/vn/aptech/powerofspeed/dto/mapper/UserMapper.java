package vn.aptech.powerofspeed.dto.mapper;

import java.util.HashSet;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import vn.aptech.powerofspeed.controller.v1.command.UserUpdateFormCommand;
import vn.aptech.powerofspeed.dto.model.user.AddressDto;
import vn.aptech.powerofspeed.dto.model.user.RoleDto;
import vn.aptech.powerofspeed.dto.model.user.UserDto;
import vn.aptech.powerofspeed.model.user.User;


@Component
public class UserMapper {

    public static UserDto toUserDto(User user) {
        return new UserDto()
                .setId(user.getId())
                .setEmail(user.getEmail())
                .setPassword(user.getPassword())
                .setPhoneNumber(user.getPhoneNumber())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setGender(user.getGender())
                .setDateOfBirth(user.getDateOfBirth())
                .setProfilePicture(user.getProfilePicture())
                .setStatus(user.isStatus())
                .setCreateAt(user.getCreateAt())
                .setUpdateAt(user.getUpdateAt())
                .setAddressDto(new ModelMapper().map(user.getAddress(), AddressDto.class))
                .setRoleDtos(new HashSet<RoleDto>(user
                    .getRoles()
                    .stream()
                    .map(role -> new ModelMapper().map(role, RoleDto.class))
                    .collect(Collectors.toSet())));
    }

    public static UserUpdateFormCommand toUserUpdate(UserDto user) {
        return new UserUpdateFormCommand()
                .setId(user.getId())
                .setEmail(user.getEmail())
                .setPassword(user.getPassword())
                .setPhoneNumber(user.getPhoneNumber())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setGender(user.getGender())
                .setDateOfBirth(user.getDateOfBirth())
                .setProfilePicture(user.getProfilePicture())
                .setAddress(user.getAddressDto().getAddress())
                .setCountry(user.getAddressDto().getCountry())
                .setCity(user.getAddressDto().getCity())
                .setStateOrRegion(user.getAddressDto().getStateOrRegion())
                .setPostalCode(user.getAddressDto().getPostalCode());

    }

}
