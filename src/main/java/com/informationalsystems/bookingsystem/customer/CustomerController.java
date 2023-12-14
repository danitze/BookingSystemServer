package com.informationalsystems.bookingsystem.customer;

import com.informationalsystems.bookingsystem.util.AuthenticationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PutMapping
    public ResponseEntity<?> update(
            Authentication authentication,
            Principal principal,
            @RequestBody CustomerDto dto
    ) {
        if (!AuthenticationUtil.isCustomer(authentication)) {
            throw new AccessDeniedException("Need to be customer");
        }
        return ResponseEntity.ok(service.update(principal, dto));
    }

}
