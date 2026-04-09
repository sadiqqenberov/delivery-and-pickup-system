package delivery_and_pickup_system.delivery_and_pickup_system.filters;

import delivery_and_pickup_system.delivery_and_pickup_system.service.security.AccessTokenManager;
import delivery_and_pickup_system.delivery_and_pickup_system.service.security.AuthBusinessService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

import static delivery_and_pickup_system.delivery_and_pickup_system.constans.TokenConstants.PREFIX;

@Component
@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {

    private final AccessTokenManager accessTokenManager;
    private final AuthBusinessService authBusinessService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {


        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (Objects.nonNull(token) && token.startsWith(PREFIX)) {
            authBusinessService.setAuthentication(
                    accessTokenManager.getEmail(
                            token.substring(7))
            );


        }
        System.out.println(token);

        filterChain.doFilter(request, response);
    }
}
