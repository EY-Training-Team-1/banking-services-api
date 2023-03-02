package com.ey.bankingservicesapi.controllers;

import com.ey.bankingservicesapi.models.Bank;
import com.ey.bankingservicesapi.models.BankForm;
import com.ey.bankingservicesapi.models.UserForm;
import com.ey.bankingservicesapi.models.Users;
import com.ey.bankingservicesapi.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BankController {

    BankService b;

    @Autowired
    public BankController(BankService bank){
        this.b=bank;

    }

    @GetMapping("/banks")
    public List<BankForm> getAllBank() {
        System.out.println("Getting All Banks");
        return b.getAllBanks().stream()
                .map(m -> b.convertToBankForm(m))
                .collect(Collectors.toList());


    }

    @GetMapping("/banks/{id}")
    public ResponseEntity<BankForm> getBankById(@PathVariable String id) {

        try {
            int num = Integer.parseInt(id);
            Bank bank = b.getBank(num);
            return new ResponseEntity<>(b.convertToBankForm(bank), HttpStatus.OK);
        } catch(NumberFormatException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/banks", consumes = "application/json", produces = "application/json")
    public ResponseEntity<BankForm> addUser(@RequestBody BankForm form) {
        Bank bank = b.convertToBank(form);
        bank = b.addBank(bank);
        return new ResponseEntity<>(b.convertToBankForm(bank), HttpStatus.CREATED);
    }

}
