package com.ey.bankaccounts.controllers;

import com.ey.bankaccounts.clients.UserClient;
import com.ey.bankaccounts.models.Bank;
import com.ey.bankaccounts.models.OpenAccountForm;
import com.ey.bankaccounts.models.User;
import com.ey.bankaccounts.services.BankAccountsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/accounts")
public class AccountsController {
    private BankAccountsService bs;

    private UserClient userClient;

    @Autowired
    public AccountsController(BankAccountsService bs) {
        this.bs = bs;
    }

    @PostMapping(value = "/openAccount")
    public ResponseEntity<Bank> openAccount(@RequestBody OpenAccountForm oForm){
        try {
            //Transactions transactions = ts.convertToTransactions(tForm);
            return new ResponseEntity<>(bs.openAccount(oForm), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("Exception occurred while opening Account:",e);
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PutMapping(value = "/closeAccount")
    public ResponseEntity<Boolean> closeAccount(@RequestParam Integer accNum){
        try {
            //Transactions transactions = ts.convertToTransactions(tForm);
            return new ResponseEntity<>(bs.closeAccount(accNum), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("Exception occurred while closing Account:",e);
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<User> getUserInfo(@RequestParam int id) {
        User user = userClient.findById(id);

        if ((user != null)) {
            System.out.print("No user found");
        } else {
            System.out.print("user found");
        }

        return ResponseEntity.ok(user);
    }
}
