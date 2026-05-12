package delivery_and_pickup_system.delivery_and_pickup_system.model.base;

import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.status.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "shipments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = false, unique = true, length = 50)
    String trackingNumber;

    @Column(nullable = false, length = 100)
    String senderName;

    @Column(nullable = false, length = 30)
    String senderPhone;

    @Column(nullable = false, length = 100)
    String receiverName;

    @Column(nullable = false, length = 30)
    String receiverPhone;

    @Column(nullable = false, length = 255)
    String deliveryAddress;

    @Column(nullable = false)
    BigDecimal weight;

    @Column(nullable = false)
    BigDecimal price;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    OrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    User createdBy;

    @OneToMany(orphanRemoval = true, mappedBy = "shipment")
    List<Assignment> assignments;

    @OneToMany(orphanRemoval = true, mappedBy = "shipment")
    List<StatusHistory> statusHistories;

    @OneToOne(orphanRemoval = true,mappedBy = "shipment")
    Return aRreturn;
}