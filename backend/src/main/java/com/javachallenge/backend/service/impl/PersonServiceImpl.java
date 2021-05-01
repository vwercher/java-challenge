package com.javachallenge.backend.service.impl;

import com.javachallenge.backend.model.Person;
import com.javachallenge.backend.repository.PersonRepository;
import com.javachallenge.backend.service.PersonService;
import com.javachallenge.backend.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private ValidateUtil validateUtil;

    @Autowired
    private PersonRepository personRepository;

    public Person save(Person person) {
        this.validateUtil.validateSavePerson(person);
        return this.personRepository.save(person);
    }

    public Person update(Integer id, Person person) {
        this.validateUtil.validateSavePerson(person);
        person.setId(id);
        return this.personRepository.save(person);
    }

    public Boolean delete(Integer id) {
        this.personRepository.deleteById(id);
        return Boolean.TRUE;
    }

    public Optional<Person> getById(Integer id) {
        return personRepository.findById(id);
    }

    public List<Person> getAll(String name) {
        if (name != null && !name.isEmpty()) {
            return this.personRepository.findAllByNameContainingIgnoreCase(name);
        }
        return this.personRepository.findAll();
    }
}
