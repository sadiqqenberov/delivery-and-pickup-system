package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

    @NotBlank
    @Size(min = 2, max = 50)
    String name;

    @NotBlank
    @Size(min = 2, max = 50)
    String surname;

    @NotBlank
    @Email
    String email;

    @NotBlank
    @Pattern(regexp = "^\\+[1-9][0-9]{7,14}$", message = "Invalid phone number format")
    private String phoneNumber;

    @NotBlank
    @Size(max = 255)
    String address;

    @NotBlank
    String role;
}