package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.pricingRule;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PricingResponseDTO {
    Double totalPrice;
}
