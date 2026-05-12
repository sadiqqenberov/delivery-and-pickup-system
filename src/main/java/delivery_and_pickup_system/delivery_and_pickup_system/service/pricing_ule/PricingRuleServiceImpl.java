package delivery_and_pickup_system.delivery_and_pickup_system.service.pricing_ule;

import delivery_and_pickup_system.delivery_and_pickup_system.exception.BaseException;
import delivery_and_pickup_system.delivery_and_pickup_system.mapper.PricingMapper;
import delivery_and_pickup_system.delivery_and_pickup_system.model.base.PricingRule;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.pricingRule.PricingDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.pricingRule.PricingResponseDTO;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.pricingRule.PricingRuleDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.pricing_rule.RuleDelivery;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.PricingRuleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class PricingRuleServiceImpl implements PricingRuleService {

    private final PricingRuleRepository pricingRuleRepository;
    private final PricingMapper pricingMapper;

    @Override
    public PricingResponseDTO calculatePrice(PricingDto dto) {

        PricingRule rule = pricingRuleRepository
                .findFirstByStandardDelivery(RuleDelivery.STANDARD)
                .orElseThrow(BaseException::notFound);

        BigDecimal totalPrice = rule.getBasePrice();

        if (dto.getWeight().compareTo(rule.getMaxWeight()) > 0) {

            BigDecimal extraWeight = dto.getWeight().subtract(rule.getMaxWeight());

            totalPrice = totalPrice.add(
                    extraWeight.multiply(rule.getExtraPricePerKg())
            );
        }

        if (dto.getDistance().compareTo(rule.getMaxDistance()) > 0) {

            BigDecimal extraDistance = dto.getDistance().subtract(rule.getMaxDistance());

            totalPrice = totalPrice.add(
                    extraDistance.multiply(rule.getExtraPricePerKm())
            );
        }

        return pricingMapper.toResponseDTO(totalPrice);
    }

    @Override
    public Iterable<PricingRule> findAll() {
        return pricingRuleRepository.findAll();
    }

    @Transactional
    @Override
    public PricingRuleDto update(int id, PricingRuleDto pricingRuleDto) {

        PricingRule pricingRule = pricingRuleRepository.findById(id);

        pricingMapper.updatePricingRuleFromDto(pricingRuleDto, pricingRule);

        PricingRule updated = pricingRuleRepository.save(pricingRule);

        return pricingMapper.toDto(updated);
    }

    @Transactional
    @Override
    public PricingRule create(PricingRuleDto pricingRuleDto) {

        PricingRule pricingRule = pricingMapper.toEntity(pricingRuleDto);

        return pricingRuleRepository.save(pricingRule);
    }
}