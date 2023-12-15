package com.agrotep.imp.exp.config;

import com.agrotep.imp.exp.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private PersonRepository personRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/home", "/resources/**", "/assets/**").permitAll()
                        .anyRequest().authenticated()
                )
//                .csrf(AbstractHttpConfigurer::disable)
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/import-export", true)
                )
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("ivan")
                        .password("secret")
                        .roles("USER")
                        .build();

        List<UserDetails> users = personRepository.findAll().stream()
                .map(p -> User.withDefaultPasswordEncoder()
                        .username(p.getLogin())
                        .password(p.getPassword())
                        .roles("USER")
                        .build())
                .collect(Collectors.toList());
        users.add(user);

        return new InMemoryUserDetailsManager(users);
    }
}