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

//    private UserServiceImpl userDetailsService;
//
//    private JwtAuthenticationEntryPoint handler;
//
//    public SecurityConfig(UserServiceImpl userDetailsService, JwtAuthenticationEntryPoint handler) {
//        this.userDetailsService = userDetailsService;
//        this.handler = handler;
//    }

//    @Bean
//    public JwtDecoder jwtDecoder() {
//        var secretKey = new SecretKeySpec(jwtSecretKey.getBytes(), "");
//        return NimbusJwtDecoder.withSecretKey(secretKey)
//                .macAlgorithm(MacAlgorithm.HS256).build();
//    }

//    @Bean
//    public JwtAuthenticationFilter jwtAuthenticationFilter() {
//        return new JwtAuthenticationFilter();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        System.out.println("Authenticate method triggered");
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//

//    @Override
//    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService((UserDetailsService) userDetailsService);
//        provider.setPasswordEncoder(passwordEncoder());
//        return provider;
//    }

//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("*");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("OPTIONS");
//        config.addAllowedMethod("HEAD");
//        config.addAllowedMethod("GET");
//        config.addAllowedMethod("PUT");
//        config.addAllowedMethod("POST");
//        config.addAllowedMethod("DELETE");
//        config.addAllowedMethod("PATCH");
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth ->auth
//                        .requestMatchers("/").permitAll()
//                        .requestMatchers("/api/rocket/**").permitAll()
//                        .requestMatchers("/auth/**").permitAll()
//                        .requestMatchers("/auth/login").permitAll()
//                        .requestMatchers("/auth/register").permitAll()
//                        .requestMatchers("/api/user/").permitAll()
//                        .anyRequest().authenticated())
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
//                .build();
//
//    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.cors().and().csrf().disable();
//        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        httpSecurity.authorizeHttpRequests((auth) -> auth.requestMatchers("/api/rocket/**","/auth/**").permitAll());
//            httpSecurity.formLogin(httpForm -> {
//                httpForm
//                        //.loginPage("http://localhost:3000/auth")
//                        .defaultSuccessUrl("/api/rocket", true) // Başarılı giriş sonrası yönlendirme
//                        .permitAll(); // Login sayfası herkese açık
//            });
//            httpSecurity.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//            return httpSecurity.build();
//    }

//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                        .allowedOrigins("http://localhost:3000") // Frontend adresiniz
//                        .allowedHeaders("*");
//            }
//        };
//    }




//    private final UserServiceImpl userServiceimpl;
//    private DataSource dataSource;
//
//    public SecurityConfig(UserServiceImpl userServiceimpl, DataSource dataSource) {
//        this.userServiceimpl = userServiceimpl;
//        this.dataSource = dataSource;
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return userServiceimpl;
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(userServiceimpl);
//        provider.setPasswordEncoder(passwordEncoder());
//        return provider;
//    }
//
////    public void configAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder){
////        try {
////            authenticationManagerBuilder.jdbcAuthentication()
////                    .dataSource(dataSource)
////                    .passwordEncoder(new BCryptPasswordEncoder())
////                    .usersByUsernameQuery("SELECT username, password, 'true' as enabled from rocket1.users where username=? and role='ADMIN'")
////                    .authoritiesByUsernameQuery("SELECT username, role FROM rocket1.users where username=?");
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////    }
////SecurityFilterChain should be changed even admin can't access to /api/user
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
//
////                .authorizeHttpRequests(authorizeHttpRequests ->
////                        authorizeHttpRequests
////                                // Admin ve User kullanıcısı için /api/* altındaki tüm URL'lere erişim
////                                .dispatcherTypeMatchers(HttpMethod.valueOf("/api/rocket")).hasRole("USER")
////                                .requestMatchers("/api/user").denyAll()
////                                .dispatcherTypeMatchers(HttpMethod.valueOf("/api/**")).hasRole("ADMIN")
////                                .anyRequest().authenticated()
////                )
////                .formLogin(formLogin -> formLogin
////
////                        .defaultSuccessUrl("/api/rocket", true) // Başarılı giriş sonrası yönlendirme
////                        .failureUrl("/error") // Hatalı giriş sonrası yönlendirme
////                        .permitAll() // Giriş sayfasına erişimi herkese açık yapar
////                )
////                .logout(logout -> logout
////                        .logoutUrl("/logout")
////                        .logoutSuccessUrl("/") // Çıkış sonrası yönlendirme
////                        .permitAll()
////                )
////                .build();
////    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();  // BCrypt algorithm for password encoding
//    }
//

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()  // CSRF'yi devre dışı bırakıyoruz
//                .authorizeRequests()
//                .dispatcherTypeMatchers("/api/auth/login").permitAll()  // Login API'ye izin ver
//                .anyRequest().authenticated();  // Diğer tüm isteklere kimlik doğrulama zorunlu
//    }

//    @Configuration
//    @EnableWebSecurity
//    public class SecurityConfig extends WebSecurityConfigurerAdapter {
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http.csrf().disable();
//        }
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


//    @Value("${FRONTEND_URL}")
//    private String fUrl;
//
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins("http://192.168.1.41:3000", "http://192.168.1.41:8080",fUrl,"http:/localhost:3000")  // iki farklı origin
//                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                        .allowedHeaders("*")
//                        .allowCredentials(true);
//            }
//        };
//    }


//
//    @Bean
//    public JdbcUserDetailsManager jdbcUserDetailsManager() {
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//        jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT username, password, 'true' as enabled FROM rocket1.users WHERE username = ?");
//        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT username, CONCAT('ROLE_', role) as authority FROM rocket1.users WHERE username = ?");//with ROLE_ prefix code can handle ROLE_ADMIN and ROLE_USER situations ow doesn't work
//        return jdbcUserDetailsManager;
//    }

}
