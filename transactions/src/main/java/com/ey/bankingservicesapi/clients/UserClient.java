package com.ey.bankingservicesapi.clients;

import com.ey.bankingservicesapi.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-access", url = "http://localhost:8081")
public interface UserClient {

    @GetMapping("/login")
    User findByName(@RequestParam String name);

    @GetMapping("/users")
    User findById(@RequestParam int id);

}