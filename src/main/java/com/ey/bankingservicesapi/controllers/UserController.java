package com.ey.bankingservicesapi.controllers;

import com.ey.bankingservicesapi.models.UserForm;
import com.ey.bankingservicesapi.models.Users;
import com.ey.bankingservicesapi.services.BankService;
import com.ey.bankingservicesapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {


    UserService u;

    @Autowired
    BankService b;

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

    @PostMapping(value = "/users", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserForm> addUser(@RequestBody UserForm form) {
        Users user = u.convertToUser(form);
        user = u.addUser(user);
        return new ResponseEntity<>(u.convertToUserForm(user), HttpStatus.CREATED);
    }

    @PutMapping("/users/{u_id}/{b_id}")
    public ResponseEntity<UserForm> linkUser(@PathVariable int u_id, @PathVariable int b_id) {
        Users user= new Users();

       user = u.getUser(u_id);

       if (user.getBanks().contains(b.getBank(b_id))){
           user=u.unlinkBank(u_id,b_id);
       } else{
         user = u.linkBank(u_id,b_id);
       }





        return new ResponseEntity<>(u.convertToUserForm(user), HttpStatus.ACCEPTED);
    }







    @DeleteMapping("/users/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable int id) {

        try {
            boolean wasDeleted = u.deleteUser(id);
            return new ResponseEntity<>(wasDeleted, (wasDeleted) ? HttpStatus.NO_CONTENT : HttpStatus.BAD_REQUEST);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
