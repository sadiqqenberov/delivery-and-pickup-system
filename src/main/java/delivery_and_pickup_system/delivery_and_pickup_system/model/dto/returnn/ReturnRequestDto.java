package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.returnn;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReturnRequestDto {
    Integer shipmentId;
    String reason;
}
