package delivery_and_pickup_system.delivery_and_pickup_system.service.status_history;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.StatusHistory;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.status_history.StatusUpdateRequest;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.status_history.TrackingResponse;

import java.util.List;

public interface StatusHistoryService {

    void updateShipmentStatus(Integer id, StatusUpdateRequest request);

    List<StatusHistory> getShipmentStatusHistory(Integer shipmentId);

    TrackingResponse getTrackingInfo(String trackingNumber);
}