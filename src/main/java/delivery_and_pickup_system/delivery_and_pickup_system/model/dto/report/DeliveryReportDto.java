package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.report;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class DeliveryReportDto {
    private Integer id;
    private String trackingNumber;
    private String courierName;
    private LocalDateTime deliveredAt;
    private Boolean success;
}
