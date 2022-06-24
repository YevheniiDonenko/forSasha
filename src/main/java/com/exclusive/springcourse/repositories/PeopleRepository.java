package com.exclusive.springcourse.repositories;

import com.exclusive.springcourse.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<Person, String> {
    Person findByEmail(String email);
}
