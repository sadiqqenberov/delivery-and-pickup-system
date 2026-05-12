package delivery_and_pickup_system.delivery_and_pickup_system.mapper;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.PricingRule;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.pricingRule.PricingResponseDTO;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.pricingRule.PricingRuleDto;
import org.mapstruct.*;

import java.math.BigDecimal;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
)
public interface PricingMapper {

    default PricingResponseDTO toResponseDTO(BigDecimal totalPrice) {
        return PricingResponseDTO.builder()
                .totalPrice(totalPrice)
                .build();
    }

    void updatePricingRuleFromDto (PricingRuleDto dto ,@MappingTarget PricingRule pricingRule);

    PricingRuleDto toDto (PricingRule pricingRule);

    PricingRule toDto (PricingRuleDto dto);
}
