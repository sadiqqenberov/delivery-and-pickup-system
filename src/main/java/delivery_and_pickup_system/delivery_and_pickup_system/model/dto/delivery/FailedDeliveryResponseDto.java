package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.delivery;

import lombok.Data;

@Data
public class FailedDeliveryResponseDto {
    Integer id;
    Integer shipmentId;
    String note;
}
