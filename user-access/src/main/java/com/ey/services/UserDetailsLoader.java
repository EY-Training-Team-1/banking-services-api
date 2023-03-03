package com.ey.services;

import com.ey.models.User;
import com.ey.models.UserWithRoles;
import com.ey.repositories.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//UserDetailsService interface is used to retrieve user-related data,
// such as username, password, and authorities, from a database or other
// data source. It is responsible for loading user-specific data that is
// used by the authentication system to verify the user's identity and
// authorization.
@Service
public class UserDetailsLoader implements UserDetailsService {

    private final UserRepo users;

    public UserDetailsLoader(UserRepo users){
        this.users = users;
    }

//    When Spring Security needs to authenticate a user, it calls the
//    loadUserByUsername method of the UserDetailsService implementation
//    to retrieve the user's data. If the user is found and their password
//    matches the one provided during authentication, Spring Security
//    creates an Authentication object, which contains the user's identity
//    and authorities.
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = users.findByName(name);

        if(user == null){
            throw new UsernameNotFoundException("No user account found for: " + name);
        }

        return new UserWithRoles(user);
    }
}