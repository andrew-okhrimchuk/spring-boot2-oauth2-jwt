package com.marcosbarbero.lab.sec.oauth.jwt.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

import static com.marcosbarbero.lab.sec.oauth.jwt.constants.MicroServiceConstants.LOGIN_MICROSERVICE;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final DataSource dataSource;
    private PasswordEncoder passwordEncoder;
    private UserDetailsService userDetailsService;
    public WebSecurityConfiguration(final DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return passwordEncoder == null ? passwordEncoder = DefaultPasswordEncoderFactories.createDelegatingPasswordEncoder() : passwordEncoder;
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        if (userDetailsService == null) {
            userDetailsService = jdbcDaoImpl();
        }
        return userDetailsService;
    }

    @Bean
    public JdbcDaoImpl jdbcDaoImpl() {
        JdbcDaoImpl jdbcDaoImpl = new JdbcDaoImpl();
        jdbcDaoImpl.setDataSource(dataSource);
        return jdbcDaoImpl;
    }
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers(LOGIN_MICROSERVICE).permitAll()
                .anyRequest().authenticated();

    }
}
