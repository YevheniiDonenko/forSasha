package com.exclusive.springcourse.services;

import com.exclusive.springcourse.model.Customer;
import com.exclusive.springcourse.repositories.CustomerRepository;
import com.exclusive.springcourse.security.CustomerDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerDetailsService implements UserDetailsService {
    private final CustomerRepository customerRepository;

    public CustomerDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> customer = customerRepository.findByUsername(username);

        if (customer.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        return new CustomerDetails(customer.get());
    }
}
