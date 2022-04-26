package org.mik.spring.service;

import org.mik.spring.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    
    private CompanyRepository repository;
    
    public CompanyService(@Autowired CompanyRepository repository) {
        this.repository = repository;
    }
}
