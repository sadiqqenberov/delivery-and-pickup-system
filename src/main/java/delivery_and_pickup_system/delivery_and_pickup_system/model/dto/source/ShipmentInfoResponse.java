package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.source;

import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.status.OrderStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShipmentInfoResponse {

    String trackingNumber;
    BigDecimal weight;
    BigDecimal price;
    OrderStatus status;
    CourierInfoResponse courier;

    BigDecimal totalPrice;

}
