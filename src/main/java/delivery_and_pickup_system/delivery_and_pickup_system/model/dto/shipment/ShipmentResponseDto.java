package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.shipment;

import lombok.Data;
import java.math.BigDecimal;

@Data
//@JsonFilter("shipments")
public class ShipmentResponseDto {

    private Integer id;
    private String trackingNumber;

    private String senderName;
    private String receiverName;
    private BigDecimal weight;
    private BigDecimal price;

    private String statusKey;
    private String statusMessage;

    private String createdByName;

}