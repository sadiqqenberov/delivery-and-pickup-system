package delivery_and_pickup_system.delivery_and_pickup_system.model.base;

import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.pricing_rule.RuleDelivery;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "pricing_rules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class PricingRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = false)
    Double maxWeight;

    @Column(nullable = false)
    Double maxDistance;

    @Column(nullable = false)
    Double basePrice;

    @Column(nullable = false)
    Double extraPricePerKg;

    @Column(nullable = false)
    Double extraPricePerKm;

    @Enumerated(EnumType.STRING)
    @Column(name = "standard_delivery", nullable = false)
    RuleDelivery standardDelivery;
}