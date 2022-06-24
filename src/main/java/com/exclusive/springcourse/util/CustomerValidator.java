package com.exclusive.springcourse.util;

import com.exclusive.springcourse.model.Customer;
import com.exclusive.springcourse.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class CustomerValidator implements Validator {
    private final CustomerService customerService;

    @Autowired
    public CustomerValidator(CustomerService customerService) {
        this.customerService = customerService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Customer.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        System.out.println("validator");
        Customer customer = (Customer) target;
        Optional<Customer> customerByUsername = customerService.findByUsername(customer.getUsername());

        if (customerByUsername.isPresent()) {
            errors.rejectValue("username", "", "This username already used");
        }
    }
}
