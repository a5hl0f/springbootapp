package org.mik.spring.service;

import org.mik.spring.entity.Country;
import org.mik.spring.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

    private CountryRepository repository;

    @Value("${own.property}")
    private Integer own;

    public CountryService(@Autowired  CountryRepository repository) {
        this.repository=repository;
    }

    public Country findBySign(String sign) {
        return repository.getBySign(sign);
    }

    public Country getById(Long id) {
        return repository.getById(id);
    }

    public Country addCountry(Country c) {
        return this.repository.save(c);
    }

}
