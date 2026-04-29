package delivery_and_pickup_system.delivery_and_pickup_system.filters;

import delivery_and_pickup_system.delivery_and_pickup_system.exception.BaseException;
import delivery_and_pickup_system.delivery_and_pickup_system.service.security.AccessTokenManager;
import delivery_and_pickup_system.delivery_and_pickup_system.service.security.AuthBusinessService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static delivery_and_pickup_system.delivery_and_pickup_system.constans.TokenConstants.PREFIX;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthorizationFilter extends OncePerRequestFilter {

    private final AccessTokenManager accessTokenManager;
    private final AuthBusinessService authBusinessService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        try {
            if (header != null && header.startsWith(PREFIX)) {

                String token = header.substring(PREFIX.length());

                String email = accessTokenManager.getEmail(token);

                authBusinessService.setAuthentication(email);
            }

            filterChain.doFilter(request, response);

        } catch (JwtException | BaseException ex) {

            log.warn("Invalid JWT: {}", ex.getMessage());

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;

        } catch (Exception ex) {

            log.error("Unexpected auth error", ex);

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}