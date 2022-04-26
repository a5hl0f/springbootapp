package org.mik.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = { "org.mik.spring.entity" })
@EnableJpaRepositories (value = {"org.mik.spring.repository" })
public class MikApplication {

    public static void main(String[] args) {
        SpringApplication.run(MikApplication.class,args);
    }
}
