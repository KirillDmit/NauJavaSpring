package ru.dmitriev.NauJavaSpring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.dmitriev.NauJavaSpring.handler.CustomAuthenticationSuccessHandler;
import ru.dmitriev.NauJavaSpring.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final CustomUserDetailsService  userDetailsService;
    private final CustomAuthenticationSuccessHandler successHandler;

    @Autowired
    public SecurityConfig(@Lazy CustomUserDetailsService  userDetailsService, CustomAuthenticationSuccessHandler successHandler) {
        this.userDetailsService = userDetailsService;
        this.successHandler = successHandler;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder()); // шифрование пароля
        return authProvider;
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/resources/**");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/home", "/register", "/login", "/css/**", "/js/**").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").hasRole("ADMIN")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .failureUrl("/login?error=true") // Перенаправление при ошибке входа
                        .successHandler(successHandler)
                        .defaultSuccessUrl("/users", true) // Перенаправление после успешного входа
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true") // URL перенаправления после выхода
                        .permitAll()
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint((request, response, authException) ->
                                response.sendRedirect("/login")
                        )
                );

        http.authenticationProvider(authenticationProvider());

        return http.build();
    }
}
