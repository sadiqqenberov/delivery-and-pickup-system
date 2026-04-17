package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.pricingRule;

import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.pricing_rule.RuleDelivery;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PricingDto {
    Double weight;
    Double distance;
    RuleDelivery standardDelivery;
}
