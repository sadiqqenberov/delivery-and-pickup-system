package delivery_and_pickup_system.delivery_and_pickup_system.service.security;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.User;
import delivery_and_pickup_system.delivery_and_pickup_system.model.properties.security.SecurityProperties;
import delivery_and_pickup_system.delivery_and_pickup_system.service.base.TokenGenerator;
import delivery_and_pickup_system.delivery_and_pickup_system.service.base.TokenReader;
import delivery_and_pickup_system.delivery_and_pickup_system.service.getters.EmailGetter;
import delivery_and_pickup_system.delivery_and_pickup_system.utils.PublicPrivateKeyUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
@RequiredArgsConstructor
public class AccessTokenManager implements TokenGenerator<User>, TokenReader<Claims>, EmailGetter {

    private final SecurityProperties properties;

    private static final String EMAIL_KEY = "email";
    private static final String USER_ID_KEY = "userId";

    @Override
    public String generate(User user) {

        Date now = new Date();
        Date expiration = new Date(
                now.getTime() + properties.getJwt().getAccessTokenValidityTime()
        );

        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim(USER_ID_KEY, user.getId())
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(PublicPrivateKeyUtils.getPrivateKey(), SignatureAlgorithm.RS256)
                .compact();
    }

    @Override
    public Claims read(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(PublicPrivateKeyUtils.getPublicKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

        } catch (JwtException e) {
            log.warn("Invalid JWT token: {}", e.getMessage());
            throw new JwtException("Invalid or expired JWT token");
        }
    }

    @Override
    public String getEmail(String token) {
        return read(token).getSubject();
    }

    public Long getUserId(String token) {
        Object value = read(token).get(USER_ID_KEY);
        return value != null ? Long.valueOf(value.toString()) : null;
    }
}