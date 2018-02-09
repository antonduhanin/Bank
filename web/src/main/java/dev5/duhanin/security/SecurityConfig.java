package dev5.duhanin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private CustomBasicAuthenticationEntryPoint customBasicAuthenticationEntryPoint;

    @Autowired
    public void globalConfig(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // All requests send to the Web Server request must be authenticated
        http
                .authorizeRequests()
                .antMatchers("/accounts", "/accounts/?", "/cards", "/cards/?", "/news", "/news/?", "/transactions", "/users/allUsers", "/users/roles/?", "/users/?")
                .hasRole("Administrator")
                .antMatchers("/accounts/?/cards", "/cards/?/replenishment/?", "/news/recipient/?", "/transactions/account", "/transactions/card", "users/")
                .hasRole("Customer")
                .antMatchers("/accounts/*/*", "/cards/*/*", "/transactions/date", "/users/?/?")
                .access("hasRole('Customer') or hasRole('Administrator')")
                .antMatchers("/users", "/news/newsForAll")
                .permitAll();

        http.csrf().disable();

        // Use AuthenticationEntryPoint to authenticate user/password
        http.httpBasic().authenticationEntryPoint(customBasicAuthenticationEntryPoint);


    }


}