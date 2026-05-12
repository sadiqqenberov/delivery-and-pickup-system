package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.pricingRule;

import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.pricing_rule.RuleDelivery;
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
    BigDecimal weight;
    BigDecimal distance;
    RuleDelivery standardDelivery;
}
