package vn.aptech.powerofspeed.model.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class UserDetailsImpl implements UserDetails {

    private User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roleSet = user.getRoles();
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();

        for (Role role : roleSet) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getPhoneNumber() {
        return this.user.getPhoneNumber();
    }

    public String getProfilePicture() {
        return this.user.getProfilePicture();
    }

    public String getFirstName() {
        return this.user.getFirstName();
    }

    public String getLastName() {
        return this.user.getLastName();
    }

    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

    public String getCreateAt() {
        return this.user.getCreateAt().toString();
    }
}
