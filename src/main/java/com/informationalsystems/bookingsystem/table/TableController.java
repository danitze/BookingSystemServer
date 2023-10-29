package com.informationalsystems.bookingsystem.table;

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
@RequestMapping("/api/v1/table")
@RequiredArgsConstructor
public class TableController {


    private final TableService service;

    @PostMapping("/add")
    public ResponseEntity<String> addTable(
            Principal principal,
            Authentication authentication,
            @RequestBody RegisterTableDto dto
    ) {
        if (!AuthenticationUtil.isRestaurant(authentication)) {
            throw new AccessDeniedException("Need to be restaurant");
        }
        return ResponseEntity.ok(service.addTable(principal, dto));
    }

}
