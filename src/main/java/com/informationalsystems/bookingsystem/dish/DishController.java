package com.informationalsystems.bookingsystem.dish;

import com.informationalsystems.bookingsystem.util.AuthenticationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/dish")
@RequiredArgsConstructor
public class DishController {

    private final DishService service;

    @PostMapping
    public ResponseEntity<?> create(
            Authentication authentication,
            Principal principal,
            @RequestBody DishDto dto
    ) {
        if (!AuthenticationUtil.isRestaurant(authentication)) {
            throw new AccessDeniedException("Need to be restaurant");
        }
        return ResponseEntity.ok(service.create(principal, dto));
    }

}
