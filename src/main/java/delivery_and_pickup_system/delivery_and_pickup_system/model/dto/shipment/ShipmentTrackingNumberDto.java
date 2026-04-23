package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.shipment;

import lombok.Data;
import java.util.UUID;

@Data
public class ShipmentTrackingNumberDto {

    private Long trackingNumber = (long) UUID.randomUUID().hashCode();

}
