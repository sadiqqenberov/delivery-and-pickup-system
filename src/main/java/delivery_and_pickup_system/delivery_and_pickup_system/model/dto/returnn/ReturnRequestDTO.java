package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.returnn;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReturnRequestDTO {
    Integer shipmentId;
    String reason;
}
