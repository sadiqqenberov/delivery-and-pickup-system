package delivery_and_pickup_system.delivery_and_pickup_system.service.security;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.User;
import delivery_and_pickup_system.delivery_and_pickup_system.model.properties.security.SecurityProperties;
import delivery_and_pickup_system.delivery_and_pickup_system.service.base.TokenGenerator;
import delivery_and_pickup_system.delivery_and_pickup_system.service.base.TokenReader;
import delivery_and_pickup_system.delivery_and_pickup_system.utils.PublicPrivateKeyUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
@Slf4j
@RequiredArgsConstructor
public class AccessTokenManager implements TokenGenerator<User> , TokenReader<Claims> {

    private final SecurityProperties properties;

    @Override
    public String generate(User obj) {
        Claims claims =Jwts.claims();
        claims.put("gmail", obj.getEmail());

        Date now = new Date();
        Date expiration = new Date(now.getTime() + properties.getJwt().getAccessTokenValidityTime());


        return Jwts.builder()
                .setSubject(String.valueOf(obj.getId()))
                .setIssuedAt(now)
                .setExpiration(expiration)
                .addClaims(claims)
                .signWith(PublicPrivateKeyUtils.getPrivateKey(), SignatureAlgorithm.RS256)
                .compact();
    }


    @Override
    public Claims read(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(PublicPrivateKeyUtils.getPublicKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
