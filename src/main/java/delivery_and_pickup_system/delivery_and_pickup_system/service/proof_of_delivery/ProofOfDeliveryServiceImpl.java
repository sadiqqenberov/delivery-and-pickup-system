package delivery_and_pickup_system.delivery_and_pickup_system.service.proof_of_delivery;

import delivery_and_pickup_system.delivery_and_pickup_system.mapper.ProofOfDeliveryMapper;
import delivery_and_pickup_system.delivery_and_pickup_system.model.base.ProofOfDelivery;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.proof_of_elivery.ProofOfDeliveryRequestDTO;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.proof_of_elivery.ProofOfDeliveryResponseDTO;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.ProofOfDeliveryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProofOfDeliveryServiceImpl implements ProofOfDeliveryService {

    ProofOfDeliveryRepository repository;
    ProofOfDeliveryMapper mapper;

    @Override
    public ProofOfDeliveryResponseDTO createProof(ProofOfDeliveryRequestDTO dto) {
        ProofOfDelivery proof = mapper.toEntity(dto);
        proof.setConfirmedAt(LocalDateTime.now());

        ProofOfDelivery savedProof = repository.save(proof);
        return mapper.toResponseDto(savedProof);
    }

    @Override
    public ProofOfDeliveryResponseDTO getProofByShipmentId(Integer shipmentId) {
        ProofOfDelivery proof = repository.findByShipmentId(shipmentId)
                .orElseThrow(() -> new RuntimeException("Bu shipment üçün təhvil məlumatı tapılmadı: " + shipmentId));

        return mapper.toResponseDto(proof);
    }
}