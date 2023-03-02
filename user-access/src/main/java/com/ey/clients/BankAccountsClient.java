package com.ey.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "bank-accounts")
public interface BankAccountsClient {
    @RequestMapping(method = RequestMethod.PUT, value = "/accounts/closeAccount")
    //@RequestMapping(value = "/accounts/closeAccount")
    public ResponseEntity<Boolean> closeAccount(@RequestParam Integer accNum);
}
