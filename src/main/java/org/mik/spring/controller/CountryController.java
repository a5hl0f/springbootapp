package org.mik.spring.controller;


import org.mik.spring.Constants;
import org.mik.spring.entity.Country;
import org.mik.spring.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/country")
public class CountryController {

    private CountryService service;

    public CountryController(@Autowired CountryService service) {
        this.service=service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> findById(Long id) {
        Country c=service.getById(id);
        return  c ==null ? ResponseEntity.notFound().build() : ResponseEntity.ok(c);
    }

    @PostMapping(consumes = Constants.MIME_JSON)
    public ResponseEntity<Country> addCountry(@RequestBody Country c) {
        this.service.addCountry(c);
        return ResponseEntity.ok(c);
    }
}
