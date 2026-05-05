package delivery_and_pickup_system.delivery_and_pickup_system.service.shipment;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Shipment;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.shipment.ShipmentDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.status_history.TrackingResponse;
import org.springframework.http.converter.json.MappingJacksonValue;

import java.util.Optional;

public interface ShipmentService {

    TrackingResponse createShipment(ShipmentDto shipmentDto);

    MappingJacksonValue findAll();

    ShipmentDto findById(int id);

    Optional<TrackingResponse> findByTrackingNumber(String trackingNumber);

    ShipmentDto update(int id, ShipmentDto shipmentDto);

    void cancelShipment(Integer id);

    void deleteShipment(int id);

}
