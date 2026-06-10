package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.status_history;

import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.status.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TrackingResponse {
    private String trackingNumber;
    private String receiverName;
    private String deliveryAddress;
    private OrderStatus status;
    private List<StatusHistoryDTO> statusHistory;
}