package delivery_and_pickup_system.delivery_and_pickup_system.service.shipment;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Shipment;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.shipment.ShipmentDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.shipment.ShipmentResponseDto;
import org.springframework.http.converter.json.MappingJacksonValue;

import java.util.Optional;

public interface ShipmentService {

    ShipmentResponseDto createShipment(ShipmentDto shipmentDto);

    MappingJacksonValue findAll();

    Shipment findById(int id);

    Optional<ShipmentResponseDto> findByTrackingNumber(String trackingNumber);

    ShipmentDto update(int id, ShipmentDto shipmentDto);

    Shipment cancelShipment(int id);

    Void deleteShipment(int id);

}
