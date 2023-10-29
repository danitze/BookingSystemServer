package com.informationalsystems.bookingsystem.util;

import com.informationalsystems.bookingsystem.data.Role;
import org.springframework.security.core.Authentication;

public class AuthenticationUtil {

    public static boolean isCustomer(Authentication authentication) {
        return authentication
                .getAuthorities()
                .stream()
                .anyMatch(authority -> authority.getAuthority().equals(Role.CUSTOMER.toString()));
    }

    public static boolean isRestaurant(Authentication authentication) {
        return authentication
                .getAuthorities()
                .stream()
                .anyMatch(authority -> authority.getAuthority().equals(Role.RESTAURANT.toString()));
    }

}
