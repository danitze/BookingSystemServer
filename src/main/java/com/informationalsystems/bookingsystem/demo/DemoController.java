package com.informationalsystems.bookingsystem.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {

    @GetMapping
    public ResponseEntity<String> sayHello(Authentication authentication) {
        System.out.println(authentication.getAuthorities().toString());
        return ResponseEntity.ok("Hello from secured endpoint");
    }

}
