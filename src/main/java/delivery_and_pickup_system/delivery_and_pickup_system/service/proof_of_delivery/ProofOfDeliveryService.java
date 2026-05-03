package delivery_and_pickup_system.delivery_and_pickup_system.service.proof_of_delivery;

import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.proof_of_elivery.ProofOfDeliveryRequestDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.proof_of_elivery.ProofOfDeliveryResponseDto;

public interface ProofOfDeliveryService {
    ProofOfDeliveryResponseDto create(ProofOfDeliveryRequestDto request);

    ProofOfDeliveryResponseDto getByShipmentId(Integer shipmentId);
}