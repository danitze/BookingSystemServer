package com.informationalsystems.bookingsystem.reservation;

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

}
