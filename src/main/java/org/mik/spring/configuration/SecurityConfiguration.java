package org.mik.spring.configuration;
/*
https://springhow.com/custom-form-login-in-spring-security/

 */
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    private UnAuthenticatedEndpointProvider unAuthenticatedEndpointProvider = new UnAuthenticatedEndpointProvider();
    private MikUserDetailsService mikUserDetailsService;
    private MikUserDetailPasswordService mikUserDetailPasswordService;

    public SecurityConfiguration(@Autowired MikUserDetailsService mikUserDetailsService,
                                 @Autowired MikUserDetailPasswordService mikUserDetailPasswordService) {
        this.mikUserDetailPasswordService=mikUserDetailPasswordService;
        this.mikUserDetailsService=mikUserDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests().anyRequest().permitAll(); // .authenticated()
//                .and()
//                .httpBasic();
//        http.csrf().disable()
//                //.sessionManagement()
//                //.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                //.and()
//                    .authorizeRequests()
//                        .antMatchers(unAuthenticatedEndpointProvider.getUnAuthenticated())
//                        .permitAll()
//                .and()
//                        .formLogin()
//                        .loginPage("/login")
//                        .permitAll()
//                .and()
//                        .logout().permitAll();

        http.headers().frameOptions().sameOrigin(); //disable();  //for h2-console!!!
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsPasswordService(this.mikUserDetailPasswordService);
        provider.setUserDetailsService(this.mikUserDetailsService);
        return provider;
    }

}
