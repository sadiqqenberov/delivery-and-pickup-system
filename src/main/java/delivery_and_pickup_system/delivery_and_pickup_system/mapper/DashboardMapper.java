package delivery_and_pickup_system.delivery_and_pickup_system.mapper;

import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.admin.DashboardResponseDto;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
)
public interface DashboardMapper {

    @Mapping(target = "totalUsers", source = "users")
    @Mapping(target = "totalShipments", source = "shipments")
    @Mapping(target = "deliveredShipments", source = "delivered")
    @Mapping(target = "cancelledShipments", source = "cancelled")
    DashboardResponseDto toDto(Long users,
                               Long shipments,
                               Long delivered,
                               Long cancelled);
}
