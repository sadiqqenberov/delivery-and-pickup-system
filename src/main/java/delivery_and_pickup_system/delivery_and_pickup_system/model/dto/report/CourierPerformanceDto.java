package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.report;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourierPerformanceDto {
    private Integer courierId;
    private String courierName;
    private long successfulDeliveries;
    private long failedDeliveries;
}
