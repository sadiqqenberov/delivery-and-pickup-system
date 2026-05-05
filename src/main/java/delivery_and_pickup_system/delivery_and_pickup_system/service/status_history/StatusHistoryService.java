package delivery_and_pickup_system.delivery_and_pickup_system.service.status_history;

import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.status_history.StatusHistoryDTO;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.status_history.StatusResponseDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.status_history.TrackingResponse;

import java.util.List;

public interface StatusHistoryService {

    void updateShipmentStatus(Integer id, StatusHistoryDTO request);

    List<StatusHistoryDTO> getShipmentStatusHistory(Integer shipmentId);

    StatusResponseDto getTrackingInfo(String trackingNumber);
}