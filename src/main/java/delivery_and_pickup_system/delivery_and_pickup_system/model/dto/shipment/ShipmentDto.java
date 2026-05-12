package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.shipment;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ShipmentDto {

    private String senderName;
    private String senderPhone;
    private String receiverName;
    private String receiverPhone;
    private String deliveryAddress;
    private BigDecimal weight;
    private BigDecimal price;

    private String createdByName;
    private String createdBySurname;
}