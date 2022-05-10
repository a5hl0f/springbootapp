package org.mik.spring.controller.rest;

import org.mik.spring.Constants;
import org.mik.spring.entity.Country;
import org.mik.spring.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/*
http  :8088/api/country/all

echo '{
            "name": "EQQQQQ",
            "sign": "QQQQ"
        }
' | http POST :8088/api/country

http DELETE :8088/api/country/6

 */

@RestController
@RequestMapping("/api/country")
public class RestCountryController {

    private CountryService service;

    public RestCountryController(@Autowired CountryService service) {
        this.service=service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> findById(Long id) {
        Country c=service.getById(id);
        return  c ==null ? ResponseEntity.notFound().build() : ResponseEntity.ok(c);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Country>> findAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                                 @RequestParam(name = "size", defaultValue = "5") int size) {
        Pageable paging= PageRequest.of(page,size);
        Page<Country> l=service.getAllCountries(paging);
        return ResponseEntity.ok(l);
    }

    @PostMapping(consumes = Constants.MIME_JSON)
    public ResponseEntity<Country> addCountry(@RequestBody Country c) {
        this.service.addCountry(c);
        return ResponseEntity.ok(c);
    }

    @PutMapping(name = "/{id}", consumes = Constants.MIME_JSON)
    public ResponseEntity<Country> updateCountry(@RequestBody Country c, @PathVariable long id) {
        Country ec=service.getById(id);
        if (ec!=null) {
            ec.setName(c.getName());
            ec.setSign(c.getSign());
            return ResponseEntity.ok(this.service.saveCountry(ec));
        }
        c.setId(id);
        return ResponseEntity.ok(this.service.saveCountry(c));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
        Country c=this.service.getById(id);
        if (c==null)
            return ResponseEntity.notFound().build();
        this.service.delete(c);
        return ResponseEntity.ok().build();
    }
}
