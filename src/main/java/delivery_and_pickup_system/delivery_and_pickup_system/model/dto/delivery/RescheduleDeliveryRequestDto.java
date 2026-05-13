package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.delivery;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RescheduleDeliveryRequestDto {

    @NotNull(message = "deliveryId cannot be null")
    private Integer deliveryId;

    @NotNull(message = "newStartTime cannot be null")
    private LocalDateTime newStartTime;
}