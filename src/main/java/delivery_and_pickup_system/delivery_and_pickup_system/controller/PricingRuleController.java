package delivery_and_pickup_system.delivery_and_pickup_system.controller;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.PricingRule;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.pricingRule.PricingResponseDTO;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.pricingRule.PricingDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.pricingRule.PricingRuleDto;
import delivery_and_pickup_system.delivery_and_pickup_system.service.pricing_ule.PricingRuleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pricing-rule")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PricingRuleController {

    final PricingRuleService pricingRuleService;

    @PostMapping("/calculate")
    public PricingResponseDTO calculate(@RequestBody PricingDto dto) throws Exception {
        return pricingRuleService.calculatePrice(dto);
    }

    @GetMapping("/all")
    public Iterable<PricingRule> findAll() {
        return pricingRuleService.findAll();
    }

    @PutMapping("/update/{id}")
    public PricingRuleDto update(@PathVariable int id,@RequestBody PricingRuleDto pricingRuleDto){
        return pricingRuleService.update(id, pricingRuleDto);
    }

    @PostMapping("/creat")
    public PricingRule create(@RequestBody PricingRuleDto pricingRuleDto){
        return pricingRuleService.create(pricingRuleDto);
    }


}
