package org.mik.spring.security;

import org.mik.spring.entity.security.MikUser;
import org.mik.spring.repository.MikUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MikUserDetailsService implements UserDetailsService {


    private MikUserDetailsMapper mapper;
    private MikUserRepository repository;

    public MikUserDetailsService(@Autowired MikUserDetailsMapper mapper, @Autowired MikUserRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MikUser usr=this.repository.findByUserName(username);
        return mapper.toUserDetails(usr);
    }
}
