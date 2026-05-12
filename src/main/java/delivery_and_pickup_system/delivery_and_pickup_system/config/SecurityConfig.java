package delivery_and_pickup_system.delivery_and_pickup_system.config;

import delivery_and_pickup_system.delivery_and_pickup_system.filters.AuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
class SecurityConfig {

    // ===================== PASSWORD ENCODER =====================
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ===================== AUTH PROVIDER =====================
    @Bean
    public AuthenticationProvider authenticationProvider(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder
    ) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    // ===================== AUTH MANAGER =====================
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // ===================== SECURITY FILTER CHAIN =====================
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            AuthorizationFilter authorizationFilter
    ) throws Exception {

        http
                .authorizeHttpRequests(request -> {

                    // Swagger
                    request.requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll();

                    // Auth endpoints
                    request.requestMatchers("/auth/logout").authenticated();
                    request.requestMatchers("/auth/token/refresh").authenticated();
                    request.requestMatchers("/auth/sign-up").permitAll();
                    request.requestMatchers("/auth/**").anonymous();

                    // Shipment
                    request.requestMatchers("/shipment/tracking/{trackingNumber}").permitAll();
                    request.requestMatchers("/shipment/cancel/{id}").permitAll();
                    request.requestMatchers("/shipment/delete/{id}").permitAll();

                    // PricingRule
                    request.requestMatchers("/pricing-rule/calculate").permitAll();
                    request.requestMatchers("/pricing-rule/all").permitAll();

                    // StatusHistory
                    request.requestMatchers("/status_history/shipments/").permitAll();

                    // Notification
                    request.requestMatchers("/notification/sms").permitAll();
                    request.requestMatchers("/notification/email").permitAll();

                    // Temporary fallback
                    request.requestMatchers("/**").authenticated();
                })
                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sm ->
                        sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }
}