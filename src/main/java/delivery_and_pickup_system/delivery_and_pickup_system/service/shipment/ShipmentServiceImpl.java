package delivery_and_pickup_system.delivery_and_pickup_system.service.shipment;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import delivery_and_pickup_system.delivery_and_pickup_system.mapper.ShipmentMapper;
import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Shipment;
import delivery_and_pickup_system.delivery_and_pickup_system.model.base.User;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.shipment.ShipmentDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.shipment.ShipmentResponseDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.status.OrderStatus;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.ShipmentRepository;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.StatusHistoryRepository;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final ShipmentMapper shipmentMapper;
    private final UserRepository userRepository;


    //todo:custom exception
    @Override
    public ShipmentResponseDto createShipment(ShipmentDto dto) {

        User user = userRepository.findByNameAndSurname(
                        dto.getCreatedByName(), dto.getCreatedBySurname())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Shipment shipment = new Shipment();

        shipment.setTrackingNumber(String.valueOf(System.currentTimeMillis()));

        shipment.setSenderName(dto.getSenderName());
        shipment.setSenderPhone(dto.getSenderPhone());
        shipment.setReceiverName(dto.getReceiverName());
        shipment.setReceiverPhone(dto.getReceiverPhone());
        shipment.setDeliveryAddress(dto.getDeliveryAddress());
        shipment.setWeight(dto.getWeight());
        shipment.setPrice(dto.getPrice());
        shipment.setCreatedBy(user);

        shipment.setStatus(OrderStatus.CREATED);

        Shipment saved = shipmentRepository.save(shipment);

        ShipmentResponseDto response = new ShipmentResponseDto();
        response.setId(saved.getId());
        response.setTrackingNumber(saved.getTrackingNumber());
        response.setSenderName(saved.getSenderName());
        response.setReceiverName(saved.getReceiverName());
        response.setWeight(saved.getWeight());
        response.setPrice(saved.getPrice());

        response.setStatusKey(saved.getStatus().key());
        response.setStatusMessage(saved.getStatus().message());

        response.setCreatedByName(saved.getCreatedBy().getName());

        return response;
    }

    @Override
    public MappingJacksonValue findAll() {
        List<Shipment> shipments = shipmentRepository.findAll();

        List<ShipmentResponseDto> shipmentDtos = shipmentMapper.toDtoList(shipments);

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("trackingNumber", "senderName", "senderPhone", "receiverName", "receiverPhone");

        FilterProvider provider = new SimpleFilterProvider().addFilter("shipments", filter);

        MappingJacksonValue value = new MappingJacksonValue(shipmentDtos);
        value.setFilters(provider);

        return value;
    }

    @Override
    public Shipment findById(int id) {
        return shipmentRepository.findById(id);
    }

    @Override
    public Optional<Shipment> findByTrackingNumber(String trackingNumber) {
        return shipmentRepository.findByTrackingNumber(trackingNumber);
    }

    @Override
    public ShipmentDto update(int id, ShipmentDto shipmentDto) {
        Shipment shipment = shipmentRepository.findById(id);

        shipmentMapper.updateShipmentFromDto(shipmentDto, shipment);

        Shipment shipmentUpdate = shipmentRepository.save(shipment);
        return shipmentMapper.toDto(shipmentUpdate);

    }

    @Override
    public Shipment cancelShipment(int id) {
        return null;
    }

    @Override
    public Void deleteShipment(int id) {
        shipmentRepository.deleteById(id);
        return null;
    }

}
