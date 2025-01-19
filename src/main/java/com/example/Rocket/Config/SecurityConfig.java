package com.example.Rocket.Config;


import com.example.Rocket.security.JwtAuthenticationEntryPoint;
import com.example.Rocket.security.JwtAuthenticationFilter;
import com.example.Rocket.security.JwtTokenProvider;
import com.example.Rocket.service.impl.UserServiceImpl;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.MacAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig{

//
//    @Bean
//    public SecurityFilterChain securityFilterChainUser(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity
//                .csrf(customizer ->customizer.disable())
//                .authorizeHttpRequests(registry -> {
//                    registry
//                            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll(); // OPTIONS isteklerine izin ver
//                    registry
//                            .requestMatchers("/login", "/css/**", "/js/**", "/images/**").permitAll();
//                    registry.requestMatchers(HttpMethod.GET, "/api/rocket").permitAll();
//                    registry.requestMatchers(HttpMethod.POST, "/api/rocket").permitAll();
//                    registry.requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll();
//                    registry.requestMatchers(HttpMethod.GET, "/api/auth/**").permitAll();
//                    registry.requestMatchers(HttpMethod.PUT, "/api/rocket/**").permitAll();
//                    registry.requestMatchers(HttpMethod.DELETE, "/api/rocket/**").permitAll();
//                    registry.anyRequest().authenticated();
//                })
//                .formLogin(httpForm -> {
//                    httpForm
////                            .loginPage("/api/auth/login")
//                            .defaultSuccessUrl("/api/rocket", true)
//                            .permitAll();
//                })
//                .build();
//    }


    private final JwtTokenProvider jwtTokenProvider;

    // Constructor Injection
    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtTokenProvider);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(customizer ->customizer.disable());
        http.authorizeHttpRequests(registry -> {
                    registry
                            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                            .requestMatchers("/login", "/css/**", "/js/**", "/images/**").permitAll()
                            .requestMatchers(HttpMethod.GET, "/auth/**").permitAll()
                            .requestMatchers("/auth/**").permitAll();});
        http.authorizeHttpRequests(request ->request.anyRequest().authenticated());
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        http.sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }
    @Autowired
    private UserDetailsService UserDetailsService;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(UserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
