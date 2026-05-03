package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.proof_of_elivery;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProofOfDeliveryRequestDto {

    private Integer shipmentId;
    private String receivedBy;
    private String signatureUrl;
    private String photoUrl;
}
