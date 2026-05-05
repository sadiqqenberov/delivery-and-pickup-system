package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.returnn;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReturnResponseDto {
    Integer id;
    Integer shipmentId;
    String status;
    String reason;
    LocalDateTime returnedAt;
}
