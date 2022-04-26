package org.mik.spring.configuration;

import org.mik.spring.security.MikUserDetailPasswordService;
import org.mik.spring.security.MikUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UnAuthenticatedEndpointProvider unAuthenticatedEndpointProvider=new UnAuthenticatedEndpointProvider();
    private MikUserDetailsService detailsService;
    private MikUserDetailPasswordService passwordService;

    public SecurityConfiguration(@Autowired MikUserDetailsService detailsService,
                                 @Autowired MikUserDetailPasswordService passwordService) {
        this.detailsService = detailsService;
        this.passwordService = passwordService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authorizeRequests()
                .antMatchers(unAuthenticatedEndpointProvider.getUnAuthenticated())
                .permitAll();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setPasswordEncoder(getPasswordEncoder());
        provider.setUserDetailsPasswordService(this.passwordService);
        provider.setUserDetailsService(this.detailsService);
        return provider;
    }
}
















