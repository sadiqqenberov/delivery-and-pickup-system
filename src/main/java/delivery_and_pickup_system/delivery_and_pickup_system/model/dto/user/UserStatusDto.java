package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.user;

import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.user.UserStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserStatusDto {

    UserStatus status;

}
