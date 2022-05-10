package org.mik.spring.security;

import org.mik.spring.entity.security.MikUser;
import org.mik.spring.repository.MikUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MikUserDetailPasswordService implements UserDetailsPasswordService {

    private MikUserRepository mikUserRepository;
    private MikUserDetailsMapper mikUserDetailsMapper;

    public MikUserDetailPasswordService(@Autowired MikUserRepository mikUserRepository,
                                        @Autowired MikUserDetailsMapper mikUserDetailsMapper) {
        this.mikUserRepository = mikUserRepository;
        this.mikUserDetailsMapper = mikUserDetailsMapper;
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        MikUser usr=mikUserRepository.findByUserName(user.getUsername());
        usr.setPassword(newPassword);
        return mikUserDetailsMapper.toUserDetails(usr);
    }
}
