package delivery_and_pickup_system.delivery_and_pickup_system.service.shipment;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import delivery_and_pickup_system.delivery_and_pickup_system.mapper.ShipmentMapper;
import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Shipment;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.shipment.ShipmentDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.status.OrderStatus;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.ShipmentRepository;
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

    @Override
    public Shipment createShipment(ShipmentDto shipmentDto) {

        //todo:burada createdby a baxarsam
        Shipment shipment = new Shipment();
        shipment.setTrackingNumber(shipmentDto.getTrackingNumber());
        shipment.setSenderName(shipmentDto.getSenderName());
        shipment.setSenderPhone(shipmentDto.getSenderPhone());
        shipment.setReceiverName(shipmentDto.getReceiverName());
        shipment.setReceiverPhone(shipmentDto.getReceiverPhone());
        shipment.setDeliveryAddress(shipmentDto.getDeliveryAddress());
        shipment.setWeight(shipmentDto.getWeight());
        shipment.setPrice(shipmentDto.getPrice());
        shipmentDto.getCreatedBy();

        shipment.setStatus(OrderStatus.CREATED);

        return shipmentRepository.save(shipment);
    }

    @Override
    public MappingJacksonValue findAll() {
        List<Shipment> shipments = shipmentRepository.findAll();

        List<ShipmentDto> shipmentDtos = shipmentMapper.toDtoList(shipments);

        SimpleBeanPropertyFilter filter =
                SimpleBeanPropertyFilter.filterOutAllExcept("trackingNumber", "senderName", "senderPhone", "receiverName", "receiverPhone");

        FilterProvider provider =
                new SimpleFilterProvider().addFilter("shipments", filter);

        MappingJacksonValue value = new MappingJacksonValue(shipmentDtos);
        value.setFilters(provider);

        return value;
    }

    @Override
    public Shipment findById(int id) {
        return  shipmentRepository.findById(id);
    }

    @Override
    public Optional<Shipment> findByTrackingNumber(Integer trackingNumber) {
        return shipmentRepository.findByTrackingNumber(trackingNumber);
    }

    @Override
    public ShipmentDto update(int id, ShipmentDto shipmentDto) {
        Shipment shipment = shipmentRepository.findById(id);

        shipmentMapper.updateShipmentFromDto(shipmentDto,shipment);

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
