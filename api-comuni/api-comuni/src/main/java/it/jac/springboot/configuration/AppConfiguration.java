package it.jac.springboot.configuration;

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
			.csrf(csrf -> csrf.disable())
			.httpBasic(httpBasic -> httpBasic.disable())
			.sessionManagement(sessionConfig -> sessionConfig.disable())
			.cors(cors -> {
				cors.configurationSource(apiConfigurationSource());
			})
            .authorizeHttpRequests(auth -> {
            	auth
            		.requestMatchers("/api/v1/auth/login").permitAll()
                	.anyRequest().authenticated();
            })
            .oauth2ResourceServer(oauth2ResourceServer ->
            	oauth2ResourceServer.jwt(Customizer.withDefaults()))
			;
        
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
