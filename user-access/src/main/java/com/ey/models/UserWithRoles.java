package com.ey.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

// UserDetails interface represents a user account and its associated
// authentication and authorization information. It provides methods to
// retrieve basic user information such as username, password, and authorities.
public class UserWithRoles extends User implements UserDetails {

    public UserWithRoles(User user){
        super(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        String roles = "";
        return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
