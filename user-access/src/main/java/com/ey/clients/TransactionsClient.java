package com.ey.clients;

import com.ey.models.Transactions;
import com.ey.models.TransactionsForm;
import com.ey.models.TransferForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "transactions")
public interface TransactionsClient {
    @RequestMapping(method = RequestMethod.POST, value = "/transactions/withdrawal")
    public ResponseEntity<Transactions> withDraw(@RequestBody TransactionsForm tForm);

    @RequestMapping(method = RequestMethod.POST, value = "/transactions/deposit")
    public ResponseEntity<Transactions> deposit(@RequestBody TransactionsForm tForm);

    @RequestMapping(method = RequestMethod.POST, value = "/transactions/transfer")
    public ResponseEntity<Transactions> transfer(@RequestBody TransferForm tForm);

}
