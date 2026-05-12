package delivery_and_pickup_system.delivery_and_pickup_system.mapper;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Shipment;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.shipment.ShipmentDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.shipment.ShipmentResponseDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.status_history.TrackingResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
)
public interface ShipmentMapper {


//    @Mapping(target = "createdByName", source = "createdBy.name")
//    @Mapping(target = "statusKey", source = "status")
    TrackingResponse toResponseDto(Shipment shipment);


    List<ShipmentResponseDto> toDtoList(List<Shipment> shipments);

    @Mapping(target = "createdByName", source = "createdBy.name")
    ShipmentDto toDto(Shipment shipment);

    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "trackingNumber", ignore = true)
    Shipment toEntity(ShipmentDto dto);

    @Mapping(target = "createdBy", ignore = true)
    void updateShipmentFromDto(ShipmentDto dto, @MappingTarget Shipment shipment);


}
