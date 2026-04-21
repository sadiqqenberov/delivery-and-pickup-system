package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.payload.signup;

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
