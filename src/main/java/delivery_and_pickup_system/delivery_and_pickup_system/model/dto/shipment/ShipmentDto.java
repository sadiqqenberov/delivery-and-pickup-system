package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.shipment;

import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.status.OrderStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShipmentDto {

    Integer trackingNumber;
    String senderName;
    String senderPhone;
    String receiverName;
    String receiverPhone;
    String deliveryAddress;
    Double weight;
    BigDecimal price;
    OrderStatus status;

    CreatedByDto createdBy;

}
