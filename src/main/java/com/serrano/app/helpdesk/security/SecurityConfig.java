package com.serrano.app.helpdesk.security;

import com.serrano.app.helpdesk.enums.RoleName;
import com.serrano.app.helpdesk.exception.CustomEntryPoint;
import com.serrano.app.helpdesk.filter.CustomAuthenticationFilter;
import com.serrano.app.helpdesk.filter.CustomAuthorizationFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private UserDetailsService service;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/h2-console/**").permitAll();
                http.headers().frameOptions().disable();
                
        http.exceptionHandling().authenticationEntryPoint(new CustomEntryPoint());
        http.exceptionHandling().accessDeniedHandler(new CustomEntryPoint());
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/login", "/api/v1/token/refresh", "/api/v1/emails**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/v1/users").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/v1/users/roles").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/v1/users").hasAnyAuthority(RoleName.ROLE_ADMIN.name());
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/v1/users/{email}").hasAnyAuthority(RoleName.ROLE_ADMIN.name());
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(encoder);
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
