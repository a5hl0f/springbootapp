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

    private MikUserRepository userCredentionalRepository;
    private MikUserDetailsMapper usersDetailsMapper;

    public MikUserDetailsService(@Autowired MikUserRepository userCredentionalRepository,
                                 @Autowired MikUserDetailsMapper usersDetailsMapper) {
        this.userCredentionalRepository = userCredentionalRepository;
        this.usersDetailsMapper = usersDetailsMapper;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MikUser usr=userCredentionalRepository.findByUserName(username);
        return usersDetailsMapper.toUserDetails(usr);
    }
}
