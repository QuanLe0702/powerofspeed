package vn.aptech.powerofspeed.security;

import org.springframework.transaction.annotation.Transactional;
import vn.aptech.powerofspeed.dto.model.user.RoleDto;
import vn.aptech.powerofspeed.dto.model.user.UserDto;
import vn.aptech.powerofspeed.model.user.User;
import vn.aptech.powerofspeed.model.user.UserDetailsImpl;
import vn.aptech.powerofspeed.repository.user.UserRepository;
import vn.aptech.powerofspeed.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("user with email " + email + " does not exist.");
        }

        return new UserDetailsImpl(user);
    }

//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        UserDto userDto = userService.findByEmail(email);
//        if (userDto != null) {
//            List<GrantedAuthority> authorities = getUserAuthority(userDto.getRoleDtos());
//            return buildUserForAuthentication(userDto, authorities);
//        } else {
//            throw new UsernameNotFoundException("user with email " + email + " does not exist.");
//        }
//    }
//
//    private List<GrantedAuthority> getUserAuthority(Set<RoleDto> roleDtos) {
//        Set<GrantedAuthority> roles = new HashSet<>();
//        roleDtos.forEach((role) -> {
//            roles.add(new SimpleGrantedAuthority(role.getName()));
//        });
//        return new ArrayList<GrantedAuthority>(roles);
//    }
//
//    private UserDetails buildUserForAuthentication(UserDto user, List<GrantedAuthority> authorities) {
//        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
//    }
}
