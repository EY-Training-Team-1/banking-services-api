package com.ey.clients;

import com.ey.models.Transactions;
import com.ey.models.TransactionsForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "bank-accounts")
public interface BankAccountsClient {
    @RequestMapping(method = RequestMethod.PUT, value = "/accounts/closeAccount")
    public ResponseEntity<Boolean> closeAccount(@RequestParam Integer accNum);

   }
