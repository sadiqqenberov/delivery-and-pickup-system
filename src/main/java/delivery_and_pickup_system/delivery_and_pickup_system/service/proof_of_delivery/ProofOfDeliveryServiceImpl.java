package delivery_and_pickup_system.delivery_and_pickup_system.service.proof_of_delivery;

import delivery_and_pickup_system.delivery_and_pickup_system.exception.BaseException;
import delivery_and_pickup_system.delivery_and_pickup_system.mapper.ProofOfDeliveryMapper;
import delivery_and_pickup_system.delivery_and_pickup_system.model.base.ProofOfDelivery;
import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Shipment;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.proof_of_elivery.ProofOfDeliveryRequestDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.proof_of_elivery.ProofOfDeliveryResponseDto;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.ProofOfDeliveryRepository;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class ProofOfDeliveryServiceImpl implements ProofOfDeliveryService {

    ProofOfDeliveryRepository repository;
    ShipmentRepository shipmentRepository;
    ProofOfDeliveryMapper mapper;

    private String generateOtp() {
        int otp = (int) (Math.random() * 900000) + 100000;
        return String.valueOf(otp);
    }

    @Override
    public ProofOfDeliveryResponseDto create(ProofOfDeliveryRequestDto request) {

        Shipment shipment = shipmentRepository.findById(request.getShipmentId())
                .orElseThrow(BaseException::shipmentNotFound);

        if (repository.existsByShipmentId(request.getShipmentId())) {
            throw BaseException.proofOfDeliveryAlreadyExists();
        }

        String otp = generateOtp();

        ProofOfDelivery pod = ProofOfDelivery.builder()
                .shipment(shipment)
                .receivedBy(request.getReceivedBy())
                .signatureUrl(request.getSignatureUrl())
                .photoUrl(request.getPhotoUrl())
                .otpCode(otp)
                .confirmedAt(LocalDateTime.now())
                .build();

        ProofOfDelivery saved = repository.save(pod);

        return mapper.toDto(saved);
    }

    @Override
    public ProofOfDeliveryResponseDto getByShipmentId(Integer shipmentId) {

        ProofOfDelivery pod = repository.findByShipmentId(shipmentId)
                .orElseThrow(BaseException::proofOfDeliveryNotFound);

        return mapper.toDto(pod);
    }
}