package com.informationalsystems.bookingsystem.reservation;

import com.informationalsystems.bookingsystem.util.AuthenticationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService service;

    @PostMapping
    public ResponseEntity<?> create(
            Principal principal,
            Authentication authentication,
            @RequestBody ReservationDto dto
    ) {
        if (!AuthenticationUtil.isCustomer(authentication)) {
            throw new AccessDeniedException("Need to be customer");
        }
        return ResponseEntity.ok(service.create(principal, dto));
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<?> update(
//            Principal principal,
//            @PathVariable("id") Long id,
//            @RequestBody ReservationDto dto
//    ) {
//        return ResponseEntity.ok(service.update(principal, id, dto));
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            Principal principal,
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(service.delete(principal, id));
    }

    @GetMapping
    public ResponseEntity<?> getAll(Principal principal) {
        return ResponseEntity.ok(service.getAll(principal));
    }

}
