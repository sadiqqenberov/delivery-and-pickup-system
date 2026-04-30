package delivery_and_pickup_system.delivery_and_pickup_system.service.customer;

import delivery_and_pickup_system.delivery_and_pickup_system.mapper.ShipmentMapper;
import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Shipment;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.shipment.ShipmentResponseDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.status_history.TrackingResponse;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepository customerRepository;
    private final ShipmentMapper shipmentMapper;

    @Override
    public List<ShipmentResponseDto> getCustomerShipments(Integer customerId) {

        List<Shipment> shipments =
                customerRepository.findByCreatedById(customerId);

        return shipmentMapper.toDtoList(shipments);
    }

    @Override
    public TrackingResponse getTracking(String trackingNumber) {

        Shipment shipment = customerRepository.findByTrackingNumber(trackingNumber)
                .orElseThrow(() -> new RuntimeException("Shipment tapılmadı: " + trackingNumber));

        return shipmentMapper.toResponseDto(shipment);
    }
}
