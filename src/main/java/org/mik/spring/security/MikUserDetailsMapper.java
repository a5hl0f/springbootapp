package org.mik.spring.security;

import org.mik.spring.entity.security.MikUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class MikUserDetailsMapper {

    UserDetails toUserDetails(MikUser user) {
        return User.withUsername(user.getUserName())
                .password(user.getPassword())
                .roles(user.getRoles().toArray(String[]::new))
                .build();
    }
}
