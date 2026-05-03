package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.proof_of_elivery;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProofOfDeliveryResponseDto {

    private Integer id;
    private String receivedBy;
    private String signatureUrl;
    private String photoUrl;
    private String otpCode;
    private LocalDateTime confirmedAt;
}