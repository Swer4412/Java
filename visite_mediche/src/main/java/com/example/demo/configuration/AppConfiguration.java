package com.example.demo.configuration;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableMethodSecurity
public class AppConfiguration {

	@Bean
	SecurityFilterChain secFilterChain(HttpSecurity http) throws Exception {
	    http
	        //Disabilita la protezione CSRF (Cross-Site Request Forgery) Utile per API REST stateless
	        .csrf(csrf -> csrf.disable())
	        
	        //Disabilita l'autenticazione HTTP Basic dato cheStiamo usando JWT, quindi non abbiamo bisogno dell'autenticazione basic
	        .httpBasic(httpBasic -> httpBasic.disable())
	        
	        //Disabilita la gestione della sessione, per API REST stateless, non abbiamo bisogno di mantenere sessioni lato server
	        .sessionManagement(sessionConfig -> sessionConfig.disable())
	        
	        //Configura CORS (Cross-Origin Resource Sharing), permette di definire quali origini possono accedere alle API
	        .cors(cors -> {
	            cors.configurationSource(apiConfigurationSource());
	        })
	        
	        //Configura le regole di autorizzazione per le richieste HTTP
	        .authorizeHttpRequests(auth -> {
	            auth
	                //Permette l'accesso pubblico all'endpoint di login
	                .requestMatchers("/api/v1/auth/login").permitAll()
	                //Richiede l'autenticazione per tutte le altre richieste
	                .anyRequest().authenticated();
	        })
	        
	        //Configura il server come OAuth 2.0 Resource Server, usa JWT (JSON Web Tokens) per l'autenticazione
	        .oauth2ResourceServer(oauth2ResourceServer ->
	            oauth2ResourceServer.jwt(Customizer.withDefaults())
	        );

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
	PasswordEncoder passEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
