package com.ey.controllers;

//import com.ey.clients.UserClient;
import com.ey.models.*;
import com.ey.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("accounts")
public class AccountController {

    @Autowired
    private final AccountRepo ar;

//    @Autowired
//    private UserClient userClient;

    @Autowired
    public AccountController(AccountRepo ar) {
        this.ar = ar;
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = (List<Account>) ar.findAll();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable int id) {
        Optional<Account> optional = ar.findById(id);

        return optional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.unprocessableEntity().build());
    }

//    @PostMapping
//    public ResponseEntity<Account> addAccount(@RequestBody Account account) {
//
//        List<User> users = userClient.findByIds(user.getName());
//
//        if(users == null || users.size() != account.getCards().size()) {
//            return ResponseEntity.badRequest().build();
//        }
//
//        quiz = qr.save(quiz);
//        return ResponseEntity.status(201).body(quiz);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz, @PathVariable int id) {
//        quiz.setId(id);
//
//        quiz = qr.save(quiz);
//        return ResponseEntity.ok(quiz);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Quiz> deleteQuiz(@PathVariable int id) {
//
//        Optional<Quiz> optional = qr.findById(id);
//
//        if(optional.isPresent()) {
//            qr.deleteById(id);
//            return ResponseEntity.ok(optional.get());
//        } else {
//            return ResponseEntity.notFound().build();
//        }


    }