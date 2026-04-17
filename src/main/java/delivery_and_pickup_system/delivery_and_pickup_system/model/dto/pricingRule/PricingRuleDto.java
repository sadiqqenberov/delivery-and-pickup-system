package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.pricingRule;

import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.pricing_rule.RuleDelivery;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PricingRuleDto {

    Double maxWeight;
    Double maxDistance;
    Double basePrice;
    Double extraPricePerKg;
    Double extraPricePerKm;

    RuleDelivery standardDelivery;

}
