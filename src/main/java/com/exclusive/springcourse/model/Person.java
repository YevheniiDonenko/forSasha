package com.exclusive.springcourse.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Person")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;
    @Column(name = "age")
    @Min(value = 0, message = "Age shouldn't be less then 0")
    private int age;
    @Column(name = "email")
    @Email(message = "email should be valid")
    @NotEmpty(message = "email shouldn't be empty")
    private String email;
}
