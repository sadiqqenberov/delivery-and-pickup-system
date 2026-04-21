package delivery_and_pickup_system.delivery_and_pickup_system.mapper;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Assignment;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.assignment.AssignmentDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
)
public interface AssignmentMapper {

    @Mapping(target = "shipmentId", source = "shipment.id")
    @Mapping(target = "courierId", source = "courier.id")
    AssignmentDto toDto(Assignment assignment);

    List<AssignmentDto> toDtoList(List<Assignment> assignments);

    @Mapping(target = "shipment", ignore = true)
    @Mapping(target = "courier", ignore = true)
    Assignment toEntity(AssignmentDto dto);

    @Mapping(target = "shipment", ignore = true)
    @Mapping(target = "courier", ignore = true)
    void updateFromDto(AssignmentDto dto, @MappingTarget Assignment assignment);
}
