package org.mik.spring.service;

import org.mik.spring.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private PersonRepository repository;

    public PersonService(@Autowired PersonRepository repository) {
        this.repository=repository;
    }
}
