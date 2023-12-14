package com.informationalsystems.bookingsystem.customer;

import com.informationalsystems.bookingsystem.data.Customer;
import com.informationalsystems.bookingsystem.data.User;
import com.informationalsystems.bookingsystem.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final UserRepository userRepository;

    @Transactional
    public SavedCustomerDto update(Principal principal, CustomerDto dto) {
        Customer customer = userRepository.findByPhoneNumber(principal.getName()).map(User::getCustomer).orElseThrow();
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        return Customer.toSavedCustomerDto(customer);
    }

}
