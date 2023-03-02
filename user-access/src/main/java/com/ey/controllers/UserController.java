package com.ey.controllers;

import com.ey.clients.BankAccountsClient;
import com.ey.models.Account;
import com.ey.models.User;
import com.ey.repositories.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/users")
public class UserController{

    @Autowired
    private UserRepo ur;
    @Autowired
    private PasswordEncoder pe;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private BankAccountsClient bankAccountsClient;

    public UserController(UserRepo ur, PasswordEncoder pe){
        this.ur = ur;
        this.pe = pe;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody User user) {
     user = ur.save(user);
     return ResponseEntity.status(201).body(user);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword());
        Authentication result = authenticationManager.authenticate(authentication);
        SecurityContextHolder.getContext().setAuthentication(result);
        return ResponseEntity.ok("Login successful");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAccount(@RequestBody User user) {
        ur.delete(user);
        return ResponseEntity.ok("Account deleted successfully");
    }

    @PutMapping(value = "/dashboard/closeAccount")
    public ResponseEntity<Boolean> closeAccount(@RequestParam Integer accNum){
        return bankAccountsClient.closeAccount(accNum);
    }

    @GetMapping
    public String home(){
        return "Home";
    }
}
