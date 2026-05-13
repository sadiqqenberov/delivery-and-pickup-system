package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

   @NotBlank(message = "Role name cannot be empty")
   @Size(min = 2, max = 50, message = "Role name must be between 2 and 50 characters")
   private String roleName;
}