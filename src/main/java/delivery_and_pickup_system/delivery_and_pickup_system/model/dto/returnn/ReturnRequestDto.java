package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.returnn;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReturnRequestDto {

    @NotNull(message = "ShipmentId cannot be null")
    private Integer shipmentId;

    @NotBlank(message = "Reason cannot be empty")
    @Size(min = 3, max = 500, message = "Reason must be between 3 and 500 characters")
    private String reason;
}