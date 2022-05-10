package org.mik.spring.security;

import org.mik.spring.entity.security.MikUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class MikUserDetailsMapper {

    UserDetails toUserDetails(MikUser usr) {
        return User.withUsername(usr.getUserName())
                .password(usr.getPassword())
                .roles(usr.getRoles().toArray(String[]::new))
                .build();
    }
}
