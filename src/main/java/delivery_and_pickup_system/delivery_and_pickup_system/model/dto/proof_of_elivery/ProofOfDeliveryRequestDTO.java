package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.proof_of_elivery;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProofOfDeliveryRequestDTO {
    Integer shipmentId;
    String receivedBy;
    String signatureUrl;
    String photoUrl;
    String otpCode;
}
