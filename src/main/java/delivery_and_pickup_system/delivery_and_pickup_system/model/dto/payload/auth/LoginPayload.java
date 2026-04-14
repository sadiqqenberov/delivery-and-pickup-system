package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.payload.auth;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginPayload {

    String email;
    String password;
    boolean rememberMe;

}
