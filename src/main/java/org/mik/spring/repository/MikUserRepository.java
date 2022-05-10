package org.mik.spring.repository;

import org.mik.spring.entity.security.MikUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MikUserRepository extends CrudRepository<MikUser, Long> {

    //@Query("SELECT u from users u where u.userName=:username")
    MikUser findByUserName(String username);

}
