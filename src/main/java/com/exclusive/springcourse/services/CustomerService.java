package com.exclusive.springcourse.services;

import com.exclusive.springcourse.model.Customer;
import com.exclusive.springcourse.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerService(CustomerRepository customerRepository,
                           PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<Customer> findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    @Transactional
    public void save(Customer customer) {
        customer.setId(UUID.randomUUID().toString());
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));

        customerRepository.save(customer);
    }
}
