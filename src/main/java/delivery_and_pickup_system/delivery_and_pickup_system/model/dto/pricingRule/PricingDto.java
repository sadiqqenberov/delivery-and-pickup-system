package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.pricingRule;

import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.pricing_rule.RuleDelivery;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PricingDto {

    @NotNull(message = "Weight cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Weight must be greater than 0")
    BigDecimal weight;

    @NotNull(message = "Distance cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Distance must be greater than 0")
    BigDecimal distance;

    @NotNull(message = "Delivery type cannot be null")
    RuleDelivery standardDelivery;
}