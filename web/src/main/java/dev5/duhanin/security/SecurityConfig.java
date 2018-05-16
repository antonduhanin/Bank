package dev5.duhanin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private CustomBasicAuthenticationEntryPoint customBasicAuthenticationEntryPoint;

    public void globalConfig(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService);
    }

    @Configuration
    static class MvcConfig extends WebMvcConfigurerAdapter {
        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("login").setViewName("login");
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // All requests send to the Web Server request must be authenticated
        http
                .authorizeRequests()
                .antMatchers("/accounts", "/cards", "/cards/?", "/news", "/news/?", "/transactions", "/users/allUsers", "/users/roles/?", "/users/allUsers")
                .hasRole("Administrator")
                .antMatchers("/users/accounts","/accounts/?/cards", "/cards/?/replenishment/?", "/news/recipient/?", "/transactions/account", "/transactions/card", "users/","/accounts/cards","/cards/state")
                .hasRole("Customer")
                .antMatchers("/accounts/*/*", "/cards/*/*", "/transactions/date", "/users/?/?","/home","/accounts/state","users/cards","users/transactions")
                .access("hasRole('Customer') or hasRole('Administrator')")
                .antMatchers( "/new","/news/newsForAll", "/resources/**","resources/static/**","/css/**","/resources/static/css/**","/index","/js/**","/resources/static/js/**","/register").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").successForwardUrl("/home").permitAll()
                .and().logout().logoutSuccessUrl("/index").permitAll();

        http.csrf().disable();

        // Use AuthenticationEntryPoint to authenticate user/password
        http.httpBasic().authenticationEntryPoint(customBasicAuthenticationEntryPoint);


    }

    /*@Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/resources/templates");
        resolver.setSuffix(".html");
        return resolver;
    }*/
}