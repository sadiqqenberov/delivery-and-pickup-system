package delivery_and_pickup_system.delivery_and_pickup_system.mapper;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Delivery;
import delivery_and_pickup_system.delivery_and_pickup_system.model.base.FailedDelivery;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.delivery.DeliveryResponseDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.delivery.FailedDeliveryResponseDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
)
public interface DeliveryMapper {

    @Mapping(source = "shipment.id", target = "shipmentId")
    @Mapping(source = "courier.id", target = "courierId")
    DeliveryResponseDto toDto(Delivery delivery);

    List<DeliveryResponseDto> toDtoList(List<Delivery> deliveries);

    @Mapping(source = "shipment.id", target = "shipmentId")
    FailedDeliveryResponseDto toDto(FailedDelivery failedDelivery);
}