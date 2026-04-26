package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.proof_of_elivery;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProofOfDeliveryResponseDTO {
    Integer id;
    String receivedBy;
    String signatureUrl;
    String photoUrl;
    LocalDateTime confirmedAt;
    Integer shipmentId;
}