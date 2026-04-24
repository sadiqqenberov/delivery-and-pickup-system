package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.delivery;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeliveryResponseDto {
    Integer id;
    Integer shipmentId;
    Integer courierId;
    LocalDateTime startedAt;
    Boolean success;
}