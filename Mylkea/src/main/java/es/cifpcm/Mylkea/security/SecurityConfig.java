package es.cifpcm.Mylkea.security;

import es.cifpcm.Mylkea.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final UsuarioService userService;

    public SecurityConfig(UsuarioService userService) {
        this.userService = userService;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        return userService; // Spring Security utilizará tu servicio personalizado
    }
    @Bean
    public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Para desarrollo, puedes deshabilitar CSRF
                .authorizeHttpRequests(authz ->
                        authz.requestMatchers("/login", "/register", "/styles/**", "/").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true) // Redirige siempre al home tras login exitoso
                        .failureUrl("/login?error")      // URL de fallo explícita
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)   // Invalida la sesión HTTP
                        .deleteCookies("JSESSIONID")     // Elimina la cookie de sesión
                        .permitAll()
                );

        return http.build();
    }

}