package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.source;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourierInfoResponse {

    Integer id;
    String name;
    String surname;
}