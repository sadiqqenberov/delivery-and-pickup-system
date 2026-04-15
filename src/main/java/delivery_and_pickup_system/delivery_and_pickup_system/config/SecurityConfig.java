package delivery_and_pickup_system.delivery_and_pickup_system.config;

import delivery_and_pickup_system.delivery_and_pickup_system.filters.AuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,
                                                         PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   AuthorizationFilter authorizationFilter
    ) throws Exception {
        return http
                .authorizeHttpRequests(request -> {
                    // Swagger UI
                    request.requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll();

                    // Auth URLs
                    request.requestMatchers("/auth/logout").authenticated();
                    request.requestMatchers("/auth/**").anonymous();

                    //Role URLs
                    request.requestMatchers("/role/creat").permitAll();
                    request.requestMatchers("/role/delete/{id}").permitAll();

                    //User URLs
                    request.requestMatchers("/users/creat").permitAll();
                    request.requestMatchers("/users/all").permitAll();
                    request.requestMatchers("/users/{id}").permitAll();
                    request.requestMatchers("/users/update/").permitAll();
                    request.requestMatchers("/users/{id}/status").permitAll();
                    request.requestMatchers("/users/{id}/role").permitAll();
                    request.requestMatchers("/users/couriers").permitAll();

                    // Test endpoints
                    request.requestMatchers("/test").authenticated();
                    request.requestMatchers("/test/no-auth").permitAll();
                    // Temporary
                    request.requestMatchers("/**").authenticated();
                })
                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
//                .exceptionHandling(eh -> eh.authenticationEntryPoint(authEntryPoint))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

//    @Component
//    @RequiredArgsConstructor
//    @Slf4j
//    public static class AuthEntryPoint implements AuthenticationEntryPoint {
//
//        @Qualifier("handlerExceptionResolver")
//        private final HandlerExceptionResolver resolver;
//
//        @Override
//        public void commence(HttpServletRequest request,
//                             HttpServletResponse response,
//                             AuthenticationException authException) throws IOException, ServletException {
//
//            authException.printStackTrace();
////            resolver.resolveException(request, response, null, BaseException.of(ErrorResponseMessages.FORBIDDEN));
//        }
//    }
}
