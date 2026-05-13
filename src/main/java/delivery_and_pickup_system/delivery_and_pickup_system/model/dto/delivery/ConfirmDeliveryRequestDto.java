package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.delivery;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ConfirmDeliveryRequestDto {

    @NotNull(message = "deliveryId cannot be null")
    Integer deliveryId;
}