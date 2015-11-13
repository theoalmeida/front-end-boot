package com.almeida;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/login.html",
                        "/dist/**",
                        "/bower_components/**",
                        "/app/user/**",
                        "/app/app.js",
                        "/less/**",
                        "/user/**",
                        "/registeruser",
                        "/user/save",
                        "/user/registeruser.html")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().failureUrl("/login?error")
                .defaultSuccessUrl("/index.html", true)
                .loginPage("/login.html")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and().csrf().disable();
    }

    @Bean
    public JdbcUserDetailsManager userDetailsManager() {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager();
        manager.setDataSource(dataSource);
        manager.setUsersByUsernameQuery(
                "select username,password, enable from users where username=?");
        manager.setAuthoritiesByUsernameQuery(
                "select username, authority from authorities where username=?");
        return manager;
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder builder)
            throws Exception {
        builder.userDetailsService(userDetailsManager());
    }

}