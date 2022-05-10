package org.mik.spring.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mik.spring.entity.Country;
import org.mik.spring.repository.CountryRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CountryServiceTest {

    @Mock
    private CountryRepository repository;

    @Autowired
    @InjectMocks
    private CountryService service;

    private Country country1;
    private Country country2;
    private List<Country> countryList;

    @BeforeEach
    public  void setup() {
        this.countryList=new ArrayList<>();
        country1=Country.builder()
                .name("Country1")
                .sign("C1")
                .build();
        country2=Country.builder()
                .name("Country2")
                .sign("C2")
                .build();

        this.countryList.add(country1);
        this.countryList.add(country2);
    }

    @AfterEach
    public void tearDown() {
        country1=country2=null;
        countryList=null;
    }

    @Test
    public void countryAdd() throws  Exception {
        when(repository.findAll()).thenReturn(countryList);
        List<Country> clist=service.getAllCountries();
        assertEquals(clist, countryList);
    }
}