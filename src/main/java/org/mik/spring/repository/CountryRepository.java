package org.mik.spring.repository;

import org.mik.spring.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    //Country findBySign(String sign);

    @Query(nativeQuery = true, value = "select c from Country c where sig=:sign")
    Country getBySign(String sign);

    Optional<Country> findBySign(String s);

}
