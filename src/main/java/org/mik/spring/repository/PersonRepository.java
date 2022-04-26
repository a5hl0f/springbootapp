package org.mik.spring.repository;

import org.mik.spring.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByIdNumber(String id);

}
