package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.user;

import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.role.RoleDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRoleDto {

    @NotNull
    @Valid
    RoleDto roleDto;
}