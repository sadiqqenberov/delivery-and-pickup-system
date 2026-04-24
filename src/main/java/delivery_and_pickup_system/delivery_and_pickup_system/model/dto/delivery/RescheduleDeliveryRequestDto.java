package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.delivery;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RescheduleDeliveryRequestDto {
    Integer deliveryId;
    LocalDateTime newStartTime;
}