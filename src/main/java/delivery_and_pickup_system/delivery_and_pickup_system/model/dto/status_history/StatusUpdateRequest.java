package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.status_history;

import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.status.OrderStatus;
import lombok.Data;

@Data
public class StatusUpdateRequest {
    OrderStatus status;
}