package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.pricingRule;

import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.pricing_rule.RuleDelivery;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PricingRuleDto {

    @NotNull(message = "Max weight cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Max weight must be greater than 0")
    Double maxWeight;

    @NotNull(message = "Max distance cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Max distance must be greater than 0")
    Double maxDistance;

    @NotNull(message = "Base price cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Base price must be greater than 0")
    Double basePrice;

    @NotNull(message = "Extra price per kg cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Extra price per kg must be greater than 0")
    Double extraPricePerKg;

    @NotNull(message = "Extra price per km cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Extra price per km must be greater than 0")
    Double extraPricePerKm;

    @NotNull(message = "Delivery type cannot be null")
    RuleDelivery standardDelivery;
}