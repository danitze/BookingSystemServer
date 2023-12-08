package com.informationalsystems.bookingsystem.table;

import com.informationalsystems.bookingsystem.util.AuthenticationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/table")
@RequiredArgsConstructor
public class TableController {


    private final TableService service;

    @PostMapping
    public ResponseEntity<?> addTable(
            Principal principal,
            Authentication authentication,
            @RequestBody RestaurantTableDto dto
    ) {
        if (!AuthenticationUtil.isRestaurant(authentication)) {
            throw new AccessDeniedException("Need to be restaurant");
        }
        return ResponseEntity.ok(service.addTable(principal, dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            Authentication authentication,
            Principal principal,
            @PathVariable("id") Long id,
            @RequestBody RestaurantTableDto dto
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
