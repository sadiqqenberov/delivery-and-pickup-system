package delivery_and_pickup_system.delivery_and_pickup_system.model.properties.security;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SecurityJwtData {

    String publicKey;
    String privateKey;
    Integer accessTokenValidityTime;
    Integer refreshTokenValidityTime;


}
