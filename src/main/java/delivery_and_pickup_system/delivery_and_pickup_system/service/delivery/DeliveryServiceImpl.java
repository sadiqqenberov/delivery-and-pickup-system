package delivery_and_pickup_system.delivery_and_pickup_system.service.delivery;

import delivery_and_pickup_system.delivery_and_pickup_system.mapper.DeliveryMapper;
import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Delivery;
import delivery_and_pickup_system.delivery_and_pickup_system.model.base.FailedDelivery;
import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Shipment;
import delivery_and_pickup_system.delivery_and_pickup_system.model.base.User;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.delivery.*;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.DeliveryRepository;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.FailedDeliveryRepository;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.ShipmentRepository;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final ShipmentRepository shipmentRepository;
    private final UserRepository userRepository;
    private final DeliveryMapper deliveryMapper;
    private final FailedDeliveryRepository failedDeliveryRepository;

    //todo: custom exception
    @Override
    public DeliveryResponseDto startDelivery(StartDeliveryRequestDto dto) {

        Shipment shipment = shipmentRepository.findById(dto.getShipmentId())
                .orElseThrow(() -> new RuntimeException("Shipment tapılmadı"));

        User courier = userRepository.findById(dto.getCourierId())
                .orElseThrow(() -> new RuntimeException("Courier tapılmadı"));

        Delivery delivery = new Delivery();
        delivery.setShipment(shipment);
        delivery.setCourier(courier);
        delivery.setStartedAt(LocalDateTime.now());
        delivery.setSuccess(false);

        Delivery saved = deliveryRepository.save(delivery);

        return deliveryMapper.toDto(saved);
    }

    //todo: custom exception
    @Override
    public DeliveryResponseDto confirmDelivery(ConfirmDeliveryRequestDto dto) {

        Delivery delivery = deliveryRepository.findById(dto.getDeliveryId())
                .orElseThrow(() -> new RuntimeException("Delivery tapılmadı"));

        if (Boolean.TRUE.equals(delivery.getSuccess())) {
            throw new RuntimeException("Delivery artıq təsdiqlənib");
        }

        delivery.setDeliveredAt(LocalDateTime.now());
        delivery.setSuccess(true);

        Delivery saved = deliveryRepository.save(delivery);

        return deliveryMapper.toDto(saved);
    }

    @Override
    public FailedDeliveryResponseDto failDelivery(FailDeliveryRequestDto dto) {

        Shipment shipment = shipmentRepository.findById(dto.getShipmentId())
                .orElseThrow(() -> new RuntimeException("Shipment tapılmadı"));

        FailedDelivery failed = FailedDelivery.builder()
                .shipment(shipment)
                .note(dto.getNote())
                .build();

        FailedDelivery saved = failedDeliveryRepository.save(failed);

        return deliveryMapper.toDto(saved);
    }

    @Override
    public DeliveryResponseDto rescheduleDelivery(RescheduleDeliveryRequestDto dto) {

        Delivery delivery = deliveryRepository.findById(dto.getDeliveryId())
                .orElseThrow(() -> new RuntimeException("Delivery tapılmadı"));

        if (Boolean.TRUE.equals(delivery.getSuccess())) {
            throw new RuntimeException("Artıq tamamlanmış delivery reschedule edilə bilməz");
        }

        delivery.setStartedAt(dto.getNewStartTime());

        Delivery saved = deliveryRepository.save(delivery);

        return deliveryMapper.toDto(saved);
    }

    @Override
    public DeliveryResponseDto getDeliveryById(Integer id) {

        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery tapılmadı"));

        return deliveryMapper.toDto(delivery);
    }
}
