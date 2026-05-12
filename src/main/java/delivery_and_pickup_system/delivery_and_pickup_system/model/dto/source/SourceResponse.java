package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.source;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SourceResponse {

    Integer id;
    String name;
    String surname;
    String phoneNumber;
    String address;

    List<ShipmentInfoResponse> shipments;

    BigDecimal totalPrice;
}
