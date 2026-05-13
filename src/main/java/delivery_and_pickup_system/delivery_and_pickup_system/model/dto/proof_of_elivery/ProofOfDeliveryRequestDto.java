package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.proof_of_elivery;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProofOfDeliveryRequestDto {

    @NotNull(message = "ShipmentId cannot be null")
    Integer shipmentId;

    @NotBlank(message = "ReceivedBy cannot be empty")
    @Size(min = 2, max = 100, message = "ReceivedBy must be between 2 and 100 characters")
    String receivedBy;

    @NotBlank(message = "Signature URL cannot be empty")
    String signatureUrl;

    @NotBlank(message = "Photo URL cannot be empty")
    String photoUrl;
}