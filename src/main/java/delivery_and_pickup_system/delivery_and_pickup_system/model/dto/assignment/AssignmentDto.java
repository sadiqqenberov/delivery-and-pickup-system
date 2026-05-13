package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.assignment;

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
public class AssignmentDto {

    @NotNull(message = "shipmentId cannot be null")
    Integer shipmentId;

    @NotNull(message = "courierId cannot be null")
    Integer courierId;
}