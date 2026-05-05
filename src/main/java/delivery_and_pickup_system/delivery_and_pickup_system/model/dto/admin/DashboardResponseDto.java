package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.admin;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class DashboardResponseDto {

    Long totalUsers;
    Long totalShipments;
    Long deliveredShipments;
    Long cancelledShipments;
}
