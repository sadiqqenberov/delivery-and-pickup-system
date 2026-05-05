package delivery_and_pickup_system.delivery_and_pickup_system.service.security;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.User;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.payload.auth.RefreshTokenDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.properties.security.SecurityProperties;
import delivery_and_pickup_system.delivery_and_pickup_system.service.base.TokenGenerator;
import delivery_and_pickup_system.delivery_and_pickup_system.service.base.TokenReader;
import delivery_and_pickup_system.delivery_and_pickup_system.service.getters.EmailGetter;
import delivery_and_pickup_system.delivery_and_pickup_system.utils.PublicPrivateKeyUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

import static delivery_and_pickup_system.delivery_and_pickup_system.constans.TokenConstants.EMAIL_KEY;

@Component
@Slf4j
@RequiredArgsConstructor
public class RefreshTokenManager implements TokenGenerator<RefreshTokenDto>, TokenReader<Claims>, EmailGetter {

    private final SecurityProperties securityProperties;


    @Override
    public String generate(RefreshTokenDto obj) {
        final User user = obj.getUser();

        Claims claims = Jwts.claims();
        claims.put("email", user.getEmail());
        claims.put("type", "REFRESH_TOKEN");

        Date now = new Date();

        return Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .setIssuedAt(now)
                .addClaims(claims)
                .signWith(PublicPrivateKeyUtils.getPrivateKey(), SignatureAlgorithm.RS256)
                .compact();
    }

    @Override
    public Claims read(String token) {
        Claims tokenData =  Jwts.parserBuilder()
                .setSigningKey(PublicPrivateKeyUtils.getPublicKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        String typeOfToken = tokenData.get("type", String.class);

        if(!"REFRESH_TOKEN".equals(typeOfToken)) {
            throw new RuntimeException("Wrong token type");
        }
        return tokenData;
    }

    @Override
    public String getEmail(String token) {
        return read(token).get(EMAIL_KEY,String.class);
    }


}
