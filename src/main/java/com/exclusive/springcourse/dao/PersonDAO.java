package com.exclusive.springcourse.dao;

import com.exclusive.springcourse.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Component
public class PersonDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Person> getAll() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("SELECT p from Person  p", Person.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Person getById(String id) {
        return sessionFactory.getCurrentSession().get(Person.class, id);
    }

    public Optional<Person> getByEmail(String email) {
        return null;
    }

    @Transactional
    public void save(Person person) {
        sessionFactory.getCurrentSession().save(person);
    }

    @Transactional
    public void update(String id, Person person) {
        Person personToBeUpdated = sessionFactory.getCurrentSession().get(Person.class, id);

        personToBeUpdated.setName(person.getName());
        personToBeUpdated.setAge(person.getAge());
        personToBeUpdated.setEmail(person.getEmail());
    }

    @Transactional
    public void delete(String id) {
        sessionFactory.getCurrentSession()
                .remove(sessionFactory.getCurrentSession().get(Person.class, id));
    }

}
