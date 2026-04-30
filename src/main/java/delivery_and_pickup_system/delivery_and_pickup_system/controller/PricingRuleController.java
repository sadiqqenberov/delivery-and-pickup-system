package delivery_and_pickup_system.delivery_and_pickup_system.controller;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.PricingRule;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.pricingRule.PricingDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.pricingRule.PricingResponseDTO;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.pricingRule.PricingRuleDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.status.OrderStatus;
import delivery_and_pickup_system.delivery_and_pickup_system.model.response.base.BaseResponse;
import delivery_and_pickup_system.delivery_and_pickup_system.service.pricing_ule.PricingRuleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pricing-rule")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PricingRuleController {

    final PricingRuleService pricingRuleService;


    @PostMapping("/calculate")
    public BaseResponse<PricingResponseDTO> calculate(@RequestBody PricingDto dto) {
        PricingResponseDTO result = pricingRuleService.calculatePrice(dto);

        return BaseResponse.success(OrderStatus.PRICE_CALCULATED, result);
    }

    @GetMapping("/all")
    public Iterable<PricingRule> findAll() {
        return pricingRuleService.findAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update/{id}")
    public PricingRuleDto update(@PathVariable int id,@RequestBody PricingRuleDto pricingRuleDto){
        return pricingRuleService.update(id, pricingRuleDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/creat")
    public PricingRule create(@RequestBody PricingRuleDto pricingRuleDto){
        return pricingRuleService.create(pricingRuleDto);
    }


}
