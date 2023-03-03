package com.ey.clients;

import com.ey.models.Transactions;
import com.ey.models.TransactionsForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "transactions")
public interface TransactionsClient {
    @RequestMapping(method = RequestMethod.POST, value = "/transactions/withdrawal")
    public ResponseEntity<Transactions> withDraw(@RequestBody TransactionsForm tForm);

}
