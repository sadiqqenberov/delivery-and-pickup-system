package delivery_and_pickup_system.delivery_and_pickup_system.service.shipment;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import delivery_and_pickup_system.delivery_and_pickup_system.exception.BaseException;
import delivery_and_pickup_system.delivery_and_pickup_system.mapper.ShipmentMapper;
import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Shipment;
import delivery_and_pickup_system.delivery_and_pickup_system.model.base.User;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.shipment.ShipmentDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.shipment.ShipmentResponseDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.status_history.TrackingResponse;
import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.status.OrderStatus;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.ShipmentRepository;
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


    @Override
    public TrackingResponse createShipment(ShipmentDto dto) {

        User user = userRepository.findFirstByNameAndSurname(
                dto.getCreatedByName(), dto.getCreatedBySurname()
        ).orElseThrow(BaseException::notFound);

        Shipment shipment = shipmentMapper.toEntity(dto);

        shipment.setTrackingNumber(String.valueOf(System.currentTimeMillis()));
        shipment.setCreatedBy(user);
        shipment.setStatus(OrderStatus.CREATED);

        Shipment saved = shipmentRepository.save(shipment);

        return shipmentMapper.toResponseDto(saved);
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
    public ShipmentDto findById(int id) {
        Shipment shipment = shipmentRepository.findById(id);

        return shipmentMapper.toDto(shipment);
    }

    @Override
    public Optional<TrackingResponse> findByTrackingNumber(String trackingNumber) {
        return shipmentRepository.findByTrackingNumber(trackingNumber)
                .map(shipmentMapper::toResponseDto);
    }

    @Override
    public ShipmentDto update(int id, ShipmentDto shipmentDto) {
        Shipment shipment = shipmentRepository.findById(id);

        shipmentMapper.updateShipmentFromDto(shipmentDto, shipment);

        Shipment shipmentUpdate = shipmentRepository.save(shipment);
        return shipmentMapper.toDto(shipmentUpdate);

    }

    @Override
    public void cancelShipment(Integer id) {

        Shipment shipment = shipmentRepository.findById(id)
                .orElseThrow(BaseException::shipmentNotFound);

        if (shipment.getStatus() == OrderStatus.CANCELLED) {
            throw BaseException.shipmentAlreadyCancelled();
        }

        if (shipment.getStatus() == OrderStatus.DELIVERED) {
            throw BaseException.deliveredShipmentCannotCancelled();
        }

        shipment.setStatus(OrderStatus.CANCELLED);

        shipmentRepository.save(shipment);
    }

    @Override
    public void deleteShipment(int id) {
        shipmentRepository.deleteById(id);
    }

}
