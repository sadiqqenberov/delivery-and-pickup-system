package delivery_and_pickup_system.delivery_and_pickup_system.service.pricing_ule;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.PricingRule;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.pricingRule.PricingResponseDTO;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.pricingRule.PricingDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.pricingRule.PricingRuleDto;

public interface PricingRuleService {

    PricingResponseDTO calculatePrice(PricingDto request);

    Iterable<PricingRule> findAll();

    PricingRuleDto update(int id, PricingRuleDto pricingRuleDto);

    PricingRule create(PricingRuleDto pricingRuleDto);

}
