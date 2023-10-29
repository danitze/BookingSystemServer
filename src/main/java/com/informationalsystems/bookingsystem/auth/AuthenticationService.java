package com.informationalsystems.bookingsystem.auth;

import com.informationalsystems.bookingsystem.config.JwtService;
import com.informationalsystems.bookingsystem.data.Customer;
import com.informationalsystems.bookingsystem.data.Restaurant;
import com.informationalsystems.bookingsystem.data.Role;
import com.informationalsystems.bookingsystem.data.User;
import com.informationalsystems.bookingsystem.repository.CustomerRepository;
import com.informationalsystems.bookingsystem.repository.RestaurantRepository;
import com.informationalsystems.bookingsystem.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final CustomerRepository customerRepository;

    private final RestaurantRepository restaurantRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthenticationResponse registerCustomer(CustomerRegisterRequest request) {
        User user = User.builder()
                .phoneNumber(request.getPhoneNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.CUSTOMER)
                .build();
        Customer customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();
        customer.setUserEntity(user);
        user.setCustomer(customer);
        userRepository.save(user);
        customerRepository.save(customer);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    @Transactional
    public AuthenticationResponse registerRestaurant(RestaurantRegisterRequest request) {
        User user = User.builder()
                .phoneNumber(request.getPhoneNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.RESTAURANT)
                .build();
        Restaurant restaurant = Restaurant.builder()
                .name(request.getName())
                .address(request.getAddress())
                .build();
        restaurant.setUserEntity(user);
        user.setRestaurant(restaurant);
        userRepository.save(user);
        restaurantRepository.save(restaurant);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getPhoneNumber(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
