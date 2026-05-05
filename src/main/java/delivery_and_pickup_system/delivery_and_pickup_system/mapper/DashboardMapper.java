package delivery_and_pickup_system.delivery_and_pickup_system.mapper;

import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.admin.DashboardResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
)public class DashboardMapper {

    public DashboardResponseDto toDto(Long users,
                                      Long shipments,
                                      Long delivered,
                                      Long cancelled) {

        return DashboardResponseDto.builder()
                .totalUsers(users)
                .totalShipments(shipments)
                .deliveredShipments(delivered)
                .cancelledShipments(cancelled)
                .build();
    }
}
