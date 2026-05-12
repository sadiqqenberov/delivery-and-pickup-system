package delivery_and_pickup_system.delivery_and_pickup_system.filters;

import delivery_and_pickup_system.delivery_and_pickup_system.constans.TokenConstants;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.UserSessionRepository;
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

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthorizationFilter extends OncePerRequestFilter {

    private final AccessTokenManager accessTokenManager;
    private final AuthBusinessService authBusinessService;
    private final UserSessionRepository userSessionRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        try {

            String header = request.getHeader(HttpHeaders.AUTHORIZATION);

            if (header == null || !header.startsWith(TokenConstants.PREFIX)) {
                filterChain.doFilter(request, response);
                return;
            }

            String token = header.substring(TokenConstants.PREFIX.length()).trim();
            String email = accessTokenManager.getEmail(token);

            if (email == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            boolean exists = userSessionRepository.findByAccessToken(token).isPresent();

            if (!exists) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return;
            }

            authBusinessService.setAuthentication(email);

            filterChain.doFilter(request, response);

        } catch (JwtException ex) {

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        } catch (Exception ex) {

            log.error("AUTH FILTER ERROR", ex);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}