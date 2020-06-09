package com.kt.springmvc.gestor.configuration;

import com.kt.springmvc.gestor.MySimpleUrlAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").hasAnyAuthority(
                "ROLE_DIRECTOR", "ROLE_TEACHER", "ROLE_PARENT", "ROLE_STUDENT")
                .antMatchers("/h2-console/**").hasAnyAuthority(
                "ROLE_DIRECTOR")
                .antMatchers("/director/**").hasAnyAuthority(
                "ROLE_DIRECTOR")
                .antMatchers("/teacher/**").hasAnyAuthority(
                "ROLE_TEACHER")
                .antMatchers("/student/**").hasAnyAuthority(
                "ROLE_STUDENT")
                .antMatchers("/parent/**").hasAnyAuthority(
                "ROLE_PARENT")
                .anyRequest().permitAll()
                .and()
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .usernameParameter("email")
                .passwordParameter("password")
                .loginProcessingUrl("/login-process")
                .failureUrl("/login?error")
                .successHandler(myAuthenticationSuccessHandler)
                .and()
                .logout().logoutSuccessUrl("/index");
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("director")
                .password(passwordEncoder.encode("password"))
                .roles("DIRECTOR");
        auth.jdbcAuthentication()
                .usersByUsernameQuery("SELECT u.email, u.password, u.active, from user u where u.email=?")
                .authoritiesByUsernameQuery("SELECT u.email, u.role, u.active, from user u where u.email=?")
                .dataSource(jdbcTemplate.getDataSource())
                .passwordEncoder(passwordEncoder);
    }
}
