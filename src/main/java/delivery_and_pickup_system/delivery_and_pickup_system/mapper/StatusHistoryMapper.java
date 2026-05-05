package delivery_and_pickup_system.delivery_and_pickup_system.mapper;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Shipment;
import delivery_and_pickup_system.delivery_and_pickup_system.model.base.StatusHistory;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.status_history.StatusHistoryDTO;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.status_history.StatusResponseDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.status_history.TrackingResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
)
public interface StatusHistoryMapper {

    StatusHistoryDTO toStatusHistoryDto(StatusHistory history);

//    @Mapping(target = "history", source = "historyList")
//    @Mapping(target = "currentStatus", source = "shipment.status")
//    TrackingResponse toTrackingResponse(Shipment shipment, List<StatusHistory> historyList);

    StatusResponseDto toStatusResponseDto(Shipment shipment);

    List<StatusHistoryDTO> toResponseList(List<StatusHistory> list);
}
