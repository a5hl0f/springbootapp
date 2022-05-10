package org.mik.spring.service;

import org.mik.spring.entity.Country;
import org.mik.spring.entity.Person;
import org.mik.spring.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private PersonRepository repository;

    public PersonService(@Autowired PersonRepository repository) {
        this.repository=repository;
    }

    public List<Person> getAllPerson() {
        return this.repository.findAll();
    }

    public Person getById(long id) {
        return this.repository.getById(id);
    }

    public Person savePerson(Person person) {
        if (person.getId()!=null) {
            Person rp = this.repository.getById(person.getId());
            if (rp==null)
                return this.repository.save(person);
            rp.setName(person.getName());
            rp.setIdNumber(person.getIdNumber());
            rp.setCountry(person.getCountry());
            rp.setAddress(person.getAddress());
            return this.repository.save(rp);
        }
        return person==null ? null : this.repository.save(person);
    }

    public void delete(Person p) {
        this.repository.delete(p);
    }

    public Page<Person> getPaginated(Pageable page) {
        return repository.findAll(page);
    }
}
