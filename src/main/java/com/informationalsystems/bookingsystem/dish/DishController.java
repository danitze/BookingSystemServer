package com.informationalsystems.bookingsystem.dish;

import com.informationalsystems.bookingsystem.util.AuthenticationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/dish")
@RequiredArgsConstructor
public class DishController {

    private final DishService service;

    @PostMapping
    public ResponseEntity<String> addDish(
            Authentication authentication,
            Principal principal,
            @RequestBody DishRegisterDto dto
    ) {
        if (!AuthenticationUtil.isRestaurant(authentication)) {
            throw new AccessDeniedException("Need to be restaurant");
        }
        return ResponseEntity.ok(service.addDish(principal, dto));
    }

}
