/*
    h2 db
    console: http://localhost:<app_port>/h2-console
    creating bcrypt hash: htpasswd -bnBC 10 "" <password> | tr -d ':\n'

    swagger: http://localhost:<app_port>/swagger-ui/index.html

    pager:
    http ":<app_port>/country/all?page=1&size=2"

thymeleaf:
https://techblogstation.com/spring-boot/thymeleaf-with-spring-boot/

docker:
mvn jib:dockerBuild
docker run -d -p 8088:8088 spring_en_dev:latest
 */
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
