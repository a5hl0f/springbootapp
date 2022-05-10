package org.mik.spring.service;

import org.mik.spring.entity.Country;
import org.mik.spring.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

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

    public List<Country> getAllCountries() {
        return this.repository.findAll();
    }

    public Page<Country> getAllCountries(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    public Country saveCountry(Country c) {
        if (c.getId()!=null) {
            Country rc = this.repository.getById(c.getId());
            if (rc==null)
                return this.repository.save(c);
            rc.setName(c.getName());
            rc.setSign(c.getSign());
            return this.repository.save(rc);
        }
        return c==null ? null : this.repository.save(c);
    }

    public void delete(Country c) {
        this.repository.delete(c);
    }
}
