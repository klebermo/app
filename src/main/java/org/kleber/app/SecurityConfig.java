package org.kleber.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
          .inMemoryAuthentication()
          .passwordEncoder(new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder(4))
          .withUser("user")
            .password("$2a$04$iapIeS6sr3F4D6.HWMDQA.QTc108EtLX/P6jSzJX/mOWE6Uaajn1K")
            .roles("USER")
            .and()
          .withUser("admin")
            .password("$2a$04$i8d3rvtHBNRd4WcQU2AE1eLBoXeh/nIaWGNhVU/qQYQlgjepun4Fq")
            .roles("USER", "ADMIN");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
          .authorizeRequests()
            .antMatchers("/**", "/css/**", "/js/**").permitAll()
            .antMatchers("/admin/**").authenticated()
            .and()
          .formLogin()
    				.loginPage("/signin")
    				.loginProcessingUrl("/login").permitAll()
    				.usernameParameter("login")
    				.passwordParameter("senha")
    				.and()
          .logout()
    				.logoutUrl("/logout");
    }
}
