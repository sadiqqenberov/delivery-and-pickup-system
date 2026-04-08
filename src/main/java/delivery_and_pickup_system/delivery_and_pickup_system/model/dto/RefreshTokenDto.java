package delivery_and_pickup_system.delivery_and_pickup_system.model.dto;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RefreshTokenDto {

    boolean rememberMe;
    User user;

}
