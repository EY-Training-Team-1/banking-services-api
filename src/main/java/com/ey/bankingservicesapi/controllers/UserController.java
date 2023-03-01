package com.ey.bankingservicesapi.controllers;

import com.ey.bankingservicesapi.models.UserForm;
import com.ey.bankingservicesapi.models.Users;
import com.ey.bankingservicesapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {


    UserService u;

    @Autowired
    public UserController(UserService service){

        this.u=service;
    }

    @GetMapping("/users")
    public List<UserForm> getAllUsers() {
        System.out.println("Getting All Users");
        return u.getAllUsers().stream()
                .map(m -> u.convertToUserForm(m))
                .collect(Collectors.toList());


    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserForm> getUserById(@PathVariable String id) {

        try {
            int num = Integer.parseInt(id);
            Users user = u.getUser(num);
            return new ResponseEntity<>(u.convertToUserForm(user), HttpStatus.OK);
        } catch(NumberFormatException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
