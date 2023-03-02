package com.ey.controllers;

import com.ey.models.User;
import com.ey.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.authentication.AuthenticationManagerFactoryBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController{

    @Autowired
    private UserRepo ur;
    @Autowired
    private PasswordEncoder pe;
    @Autowired
    private AuthenticationManager authenticationManager;

    public UserController(UserRepo ur, PasswordEncoder pe){
        this.ur = ur;
        this.pe = pe;
    }

    //creates new user account by checking if name already exists, encodes pw
    // and saves user to db
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody User user) {
        if (ur.findByName(user.getName()) != null) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        user.setPassword(pe.encode(user.getPassword()));
        ur.save(user);
        return ResponseEntity.ok("User created successfully");
    }

    //logs user in by auth creds, sets auth user in securitycontextholder,
    //and returns success response to client
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword());
        Authentication result = authenticationManager.authenticate(authentication);
        SecurityContextHolder.getContext().setAuthentication(result);
        return ResponseEntity.ok("Login successful");
    }
}
