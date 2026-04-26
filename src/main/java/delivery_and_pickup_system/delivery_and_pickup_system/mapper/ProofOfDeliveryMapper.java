package delivery_and_pickup_system.delivery_and_pickup_system.mapper;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.ProofOfDelivery;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.proof_of_elivery.ProofOfDeliveryRequestDTO;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.proof_of_elivery.ProofOfDeliveryResponseDTO;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
)public interface ProofOfDeliveryMapper {

    @Mapping(target = "shipment.id", source = "shipmentId")
    ProofOfDelivery toEntity(ProofOfDeliveryRequestDTO dto);

    @Mapping(target = "shipmentId", source = "shipment.id")
    ProofOfDeliveryResponseDTO toResponseDto(ProofOfDelivery entity);
}