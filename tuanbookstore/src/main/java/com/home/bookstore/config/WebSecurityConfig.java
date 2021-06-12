package com.home.bookstore.config;

import com.home.bookstore.security.SecurityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService(){
        return new SecurityService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/").authenticated()
//                .antMatchers("/new").hasAuthority("ADMIN")
                .antMatchers("/edit/**").hasAnyAuthority("ADMIN","EDITOR")
                .antMatchers("/delete/**").hasAnyAuthority("ADMIN","EDITOR")
                .antMatchers("/users/**").hasAnyAuthority("ADMIN","EDITOR")
                .antMatchers("/books/**").hasAnyAuthority("ADMIN","EDITOR")
                .antMatchers("/authors/**").hasAnyAuthority("ADMIN","EDITOR")
                .antMatchers("/publishers/**").hasAnyAuthority("ADMIN","EDITOR")
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/register/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password").permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403")
                ;
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }


}
