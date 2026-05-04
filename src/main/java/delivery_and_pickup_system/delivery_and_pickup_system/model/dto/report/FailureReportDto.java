package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.report;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FailureReportDto {
    private Integer id;
    private String trackingNumber;
    private String note;
}
