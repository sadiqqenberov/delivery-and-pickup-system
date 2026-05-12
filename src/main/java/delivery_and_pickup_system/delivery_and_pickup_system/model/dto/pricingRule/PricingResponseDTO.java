package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.pricingRule;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PricingResponseDTO {
    BigDecimal totalPrice;
}
