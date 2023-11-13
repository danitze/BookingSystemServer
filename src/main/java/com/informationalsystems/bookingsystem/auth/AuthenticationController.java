package com.informationalsystems.bookingsystem.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;

    @PostMapping("/register/customer")
    public ResponseEntity<AuthenticationResponse> registerCustomer(
            @RequestBody CustomerRegisterRequest request
    ) {
        return ResponseEntity.ok(authService.registerCustomer(request));
    }

    @PostMapping("/register/restaurant")
    public ResponseEntity<AuthenticationResponse> registerRestaurant(
            @RequestBody RestaurantRegisterRequest request
    ) {
        return ResponseEntity.ok(authService.registerRestaurant(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @GetMapping
    public ResponseEntity<?> read(
            Principal principal
    ) {
        return ResponseEntity.ok(authService.read(principal));
    }

}
