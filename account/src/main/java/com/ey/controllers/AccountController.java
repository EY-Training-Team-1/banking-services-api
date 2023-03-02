package com.ey.controllers;

import com.ey.clients.UserClient;
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

    @Autowired
    private UserClient userclient;


    @Autowired
    public AccountController(AccountRepo ar) {
        this.ar = ar;
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = (List<Account>) ar.findAll();
        return ResponseEntity.ok(accounts);
    }

    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody Account account) {
        ar.save(account);
        return ResponseEntity.ok("Account created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable(value = "id") int id) {
        Optional<Account> account = ar.findById(id);
        return account.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.unprocessableEntity().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@RequestBody Account accountDetails, @PathVariable int id) {
        accountDetails.setId(id);
        accountDetails = ar.save(accountDetails);
        return ResponseEntity.ok(accountDetails);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable(value = "id") int id){
        Optional<Account> account = ar.findById(id);
        if(account.isPresent()) {
            ar.deleteById(id);
            return ResponseEntity.ok(account.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}