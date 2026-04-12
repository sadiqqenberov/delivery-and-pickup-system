package delivery_and_pickup_system.delivery_and_pickup_system.model.base;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Entity
@Table(name = "shipments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String trackingNumber;

    String senderName;
    String senderPhone;

    String receiverName;
    String receiverPhone;

    String deliveryAddress;

    Double weight;

    BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    User createdBy;
}

