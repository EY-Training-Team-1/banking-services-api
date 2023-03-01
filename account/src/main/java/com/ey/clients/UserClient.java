//package com.ey.clients;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@FeignClient(name = "user-access")
//public interface UserClient {
//
//    @GetMapping("/login")
//    public User findByName(@RequestParam String name);
//
//}