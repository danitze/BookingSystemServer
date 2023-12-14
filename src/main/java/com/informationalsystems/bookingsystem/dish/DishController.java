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

    @GetMapping("/{id}")
    public ResponseEntity<?> read(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(service.read(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            Authentication authentication,
            Principal principal,
            @PathVariable("id") Long id,
            @RequestBody DishDto dto
    ) {
        if (!AuthenticationUtil.isRestaurant(authentication)) {
            throw new AccessDeniedException("Need to be restaurant");
        }
        return ResponseEntity.ok(service.update(principal, id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            Authentication authentication,
            Principal principal,
            @PathVariable("id") Long id
    ) {
        if (!AuthenticationUtil.isRestaurant(authentication)) {
            throw new AccessDeniedException("Need to be restaurant");
        }
        return ResponseEntity.ok(service.delete(principal, id));
    }

}
