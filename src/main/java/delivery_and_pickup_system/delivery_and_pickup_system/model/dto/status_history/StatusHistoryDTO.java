package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.status_history;

import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.status.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StatusHistoryDTO {
    private OrderStatus status;
    private LocalDateTime changedAt;
    private String note;
}