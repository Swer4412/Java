package com.example.demo.configuration;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.demo.jwt.JwtAuthenticationFilter;
import com.example.demo.service.JwtService;

@Configuration
@EnableWebSecurity
public class AppConfiguration {

    private final JwtService jwtService;

    public AppConfiguration(JwtService jwtService) {
        this.jwtService = jwtService;
    }
    
    //Senza filtri di sicurezza
    /*@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())  // Disabilita CSRF
        .cors(cors -> cors.disable())  // Disabilita CORS
        .authorizeHttpRequests(auth -> auth
            .anyRequest().authenticated()  // Richiede autenticazione per tutte le richieste
        )
        .httpBasic(Customizer.withDefaults())  // Abilita l'autenticazione HTTP Basic
        .sessionManagement(session -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // Mantiene la policy STATELESS
        );

    return http.build();
}*/
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .cors(cors -> cors.configurationSource(apiConfigurationSource()))
            .authorizeRequests()
                .requestMatchers("/api/v1/auth/login", "/api/v1/auth/register").permitAll()
                .anyRequest().authenticated()
            .and()
            //Passo il filtro con parametro service per motivi di dipendenze
            .addFilterBefore(new JwtAuthenticationFilter(jwtService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    CorsConfigurationSource apiConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
//		abilito tutti i domini ad eseguire chiamate verso il server
//		attenzione per * significa tutti, in realtà dovrebbero essere elencati
//		i soli domini abilitati
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}