package org.mik.spring.controller.rest;


import org.mik.spring.Constants;
import org.mik.spring.entity.Country;
import org.mik.spring.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Page<Country>>  findAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                                  @RequestParam(name = "size", defaultValue = "5") int size) {
        Pageable paging= PageRequest.of(page, size);
        Page<Country> result = service.getAllCountries(paging);
        return ResponseEntity.ok(result);
    }

    @PutMapping(value = "/{id}", consumes = Constants.MIME_JSON)
    public ResponseEntity<Country> updateCountry(@RequestBody Country c, @PathVariable Long id) {
        c.setId(id);
        return ResponseEntity.ok(service.saveCountry(c));
    }

    @PostMapping(consumes = Constants.MIME_JSON)
    public ResponseEntity<Country> addCountry(@RequestBody Country c) {
        this.service.addCountry(c);
        return ResponseEntity.ok(c);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
        Country c=service.getById(id);
        if (c==null)
            return ResponseEntity.notFound().build();
        service.deleteCountry(c);
        return ResponseEntity.ok().build();
    }

}











