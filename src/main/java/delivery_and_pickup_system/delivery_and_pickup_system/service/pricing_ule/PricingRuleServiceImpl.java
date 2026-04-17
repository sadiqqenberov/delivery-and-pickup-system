package delivery_and_pickup_system.delivery_and_pickup_system.service.pricing_ule;

import delivery_and_pickup_system.delivery_and_pickup_system.exception.BaseException;
import delivery_and_pickup_system.delivery_and_pickup_system.mapper.PricingMapper;
import delivery_and_pickup_system.delivery_and_pickup_system.model.base.PricingRule;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.pricingRule.PricingResponseDTO;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.pricingRule.PricingDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.pricingRule.PricingRuleDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.pricing_rule.RuleDelivery;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.PricingRuleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PricingRuleServiceImpl implements PricingRuleService {

    private final PricingRuleRepository pricingRuleRepository;
    private final PricingMapper pricingMapper;


    @Override
    public PricingResponseDTO calculatePrice(PricingDto dto) {

        PricingRule rule = pricingRuleRepository
                .findFirstByStandardDelivery(String.valueOf(RuleDelivery.STANDARD))
                .orElseThrow(BaseException::new);

        double totalPrice = rule.getBasePrice();

        if (dto.getWeight() > rule.getMaxWeight()) {
            double extraWeight = dto.getWeight() - rule.getMaxWeight();
            totalPrice += extraWeight * rule.getExtraPricePerKg();
        }

        if (dto.getDistance() > rule.getMaxDistance()) {
            double extraDistance = dto.getDistance() - rule.getMaxDistance();
            totalPrice += extraDistance * rule.getExtraPricePerKm();
        }

        return pricingMapper.toResponseDTO(totalPrice);
    }

    @Override
    public Iterable<PricingRule> findAll() {
        return  pricingRuleRepository.findAll();
    }

    @Override
    public PricingRuleDto update(int id, PricingRuleDto pricingRuleDto) {

        PricingRule pricingRule = pricingRuleRepository.findById(id);

        pricingMapper.updatePricingRuleFromDto(pricingRuleDto, pricingRule);

        PricingRule updatedPricingRule = pricingRuleRepository.save(pricingRule);

        return pricingMapper.toDto(updatedPricingRule);

    }

    @Override
    public PricingRule create(PricingRuleDto pricingRuleDto) {

        PricingRule pricingRule = pricingMapper.toDto(pricingRuleDto);

        return pricingRuleRepository.save(pricingRule);

    }
}
