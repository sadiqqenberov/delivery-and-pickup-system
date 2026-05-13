package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.delivery;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StartDeliveryRequestDto {

    @NotNull(message = "shipmentId cannot be null")
    private Integer shipmentId;

    @NotNull(message = "courierId cannot be null")
    private Integer courierId;
}