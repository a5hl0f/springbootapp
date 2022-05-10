package org.mik.spring.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mik.spring.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CountryRepositoryTest {

    @Autowired
    private CountryRepository repository;
    private Country country;

    @BeforeEach
    public void setup() {
        this.country=Country.builder()
                .name("TCountry")
                .sign("TC")
                .id(1L)
                .build();
    }

    @AfterEach
    public void tearDown() {
        this.repository.deleteAll();
    }

    @Test
    void saveTest() {
        this.repository.save(this.country);
        Optional<Country> fetchedCountry=this.repository.findBySign("TC");
        assertTrue(fetchedCountry.isPresent());


    }

}