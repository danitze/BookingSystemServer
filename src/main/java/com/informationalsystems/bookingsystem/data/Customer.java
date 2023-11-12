package com.informationalsystems.bookingsystem.data;

import com.informationalsystems.bookingsystem.customer.SavedCustomerDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customer")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Reservation> reservations = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @OneToOne
    private User userEntity;

    public static SavedCustomerDto toSavedCustomerDto(Customer customer) {
        return SavedCustomerDto.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .phoneNumber(customer.getUserEntity().getPhoneNumber())
                .build();
    }

}
