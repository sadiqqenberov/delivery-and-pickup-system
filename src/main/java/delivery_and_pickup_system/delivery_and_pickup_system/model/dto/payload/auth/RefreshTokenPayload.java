package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.payload.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RefreshTokenPayload {

    @NotBlank(message = "Refresh token cannot be empty")
    String refreshToken;

    boolean rememberMe;
}