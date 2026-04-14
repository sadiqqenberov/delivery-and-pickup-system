package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.payload.signup;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Role;
import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.user.UserStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignUpPayload {

    String name;
    String surname;
//    Integer role;
    String email;
    String phoneNumber;
    String password;
    String address;
}
