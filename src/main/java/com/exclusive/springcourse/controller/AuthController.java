package com.exclusive.springcourse.controller;

import com.exclusive.springcourse.model.Customer;
import com.exclusive.springcourse.services.CustomerService;
import com.exclusive.springcourse.util.CustomerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final CustomerValidator customerValidator;
    private final CustomerService customerService;

    @Autowired
    public AuthController(CustomerValidator customerValidator,
                          CustomerService customerService) {
        this.customerValidator = customerValidator;
        this.customerService = customerService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("customer") Customer customer) {
        System.out.println("getController");
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("customer") @Valid Customer customer,
                                      BindingResult bindingResult) {
        System.out.println("postController");

        customerValidator.validate(customer, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/auth/registration";
        }

        customerService.save(customer);

        System.out.println(customer);

        return "redirect:/auth/login";
    }
}
