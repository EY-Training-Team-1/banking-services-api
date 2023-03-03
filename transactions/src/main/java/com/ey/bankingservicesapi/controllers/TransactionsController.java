package com.ey.bankingservicesapi.controllers;

import com.ey.bankingservicesapi.models.Transactions;
import com.ey.bankingservicesapi.models.TransactionsForm;
import com.ey.bankingservicesapi.models.TransferForm;
import com.ey.bankingservicesapi.services.TransactionsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/transactions")
public class TransactionsController {

    private TransactionsService ts;
    @Autowired
    public TransactionsController(TransactionsService ts) {
        this.ts = ts;
    }

    @PostMapping(value = "/withdrawal")
    public ResponseEntity<Transactions> withDraw(@RequestBody TransactionsForm tForm){
        try {
            Transactions transactions = ts.convertToTransactions(tForm);
            return new ResponseEntity<>(ts.makeWithDrawl(transactions), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("Exception occurred while making withdrawl:",e);
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PostMapping(value = "/deposit")
    public ResponseEntity<Transactions> deposit(@RequestBody TransactionsForm tForm){
        try {
            Transactions transactions = ts.convertToTransactions(tForm);
            return new ResponseEntity<>(ts.makeDeposit(transactions), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("Exception occurred while making deposit:",e);
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PostMapping(value = "/transfer")
    public ResponseEntity<Transactions> transfer(@RequestBody TransferForm tForm){
        try {
            Transactions transactions = ts.convertToTransactions(tForm);
            return new ResponseEntity<>(ts.makeTransfer(transactions), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("Exception occurred while making transfer:",e);
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

}
