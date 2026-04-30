package delivery_and_pickup_system.delivery_and_pickup_system.service.customer;

import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.shipment.ShipmentResponseDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.status_history.TrackingResponse;

import java.util.List;

public interface CustomerService {

    List<ShipmentResponseDto> getCustomerShipments(Integer customerId);

    TrackingResponse getTracking(String trackingNumber);

}
