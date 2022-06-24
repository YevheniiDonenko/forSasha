package com.exclusive.springcourse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Customer")
@Data
public class Customer {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "username")
    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 2, max = 15, message = "Name should be between 2 and 15 characters")
    private String username;

    @Column(name = "year_of_birth")
    @Min(value = 1900, message = "Year of birth should be more than 1900")
    private int yearOfBirth;


    @Column(name = "password")
    @NotEmpty(message = "Password shouldn't be empty")
    private String password;
}
