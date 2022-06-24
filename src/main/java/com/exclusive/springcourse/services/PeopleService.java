package com.exclusive.springcourse.services;

import com.exclusive.springcourse.model.Person;
import com.exclusive.springcourse.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> getAll() {
        return peopleRepository.findAll();
    }

    public Person getById(String id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }

    public Optional<Person> getByEmail(String email) {
        return Optional.ofNullable(peopleRepository.findByEmail(email));
    }

    @Transactional
    public void save(@Valid Person person) {
        person.setId(UUID.randomUUID().toString());
        peopleRepository.save(person);
    }

    @Transactional
    public void update(String id, Person person) {
        person.setId(id);
        peopleRepository.save(person);
    }

    @Transactional
    public void delete(String id) {
        peopleRepository.deleteById(id);
    }
}
