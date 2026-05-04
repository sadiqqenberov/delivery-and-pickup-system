package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.report;

import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.status.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ReturnReportDto {
    private Integer id;
    private String trackingNumber;
    private OrderStatus status;
    private LocalDateTime returnedAt;
}
