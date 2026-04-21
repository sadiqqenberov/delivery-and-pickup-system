package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.shipment;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ShipmentResponseDto {

    private Integer id;
    private Integer trackingNumber;
    private String senderName;
    private String receiverName;
    private Double weight;
    private BigDecimal price;

    private String statusKey;
    private String statusMessage;

    private String createdByName;
}
