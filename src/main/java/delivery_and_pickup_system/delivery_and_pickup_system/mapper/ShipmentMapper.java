package delivery_and_pickup_system.delivery_and_pickup_system.mapper;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Shipment;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.shipment.ShipmentDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
)
public interface ShipmentMapper {

    List<ShipmentDto> toDtoList(List<Shipment> shipments);

    void updateShipmentFromDto(ShipmentDto dto, @MappingTarget Shipment shipment);

    ShipmentDto toDto(Shipment shipment);

    Shipment toEntity(ShipmentDto dto);



}
