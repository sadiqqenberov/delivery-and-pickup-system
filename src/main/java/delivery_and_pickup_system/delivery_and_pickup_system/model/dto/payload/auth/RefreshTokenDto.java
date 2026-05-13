package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.payload.auth;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.User;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RefreshTokenDto {

    boolean rememberMe;

    @NotNull(message = "User cannot be null")
    User user;
}