package delivery_and_pickup_system.delivery_and_pickup_system.model.base;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "proof_of_delivery")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProofOfDelivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String receivedBy;

    private String signatureUrl;

    private String photoUrl;

    private String otpCode;

    private LocalDateTime confirmedAt;

    @OneToOne
    @JoinColumn(name = "shipment_id")
    private Shipment shipment;
}