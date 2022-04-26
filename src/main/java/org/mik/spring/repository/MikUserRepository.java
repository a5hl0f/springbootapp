package org.mik.spring.repository;

import org.mik.spring.entity.security.MikUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MikUserRepository extends JpaRepository<MikUser, Long> {

    MikUser findByUserName(String un);

}
