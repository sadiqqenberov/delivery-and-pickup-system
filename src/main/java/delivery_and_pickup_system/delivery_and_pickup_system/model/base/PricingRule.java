package delivery_and_pickup_system.delivery_and_pickup_system.model.base;

import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.pricing_rule.RuleDelivery;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "pricing_rules")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PricingRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    Double maxWeight;
    Double maxDistance;
    Double basePrice;
    Double extraPricePerKg;
    Double extraPricePerKm;
    @Enumerated(EnumType.STRING)
    @Column(name = "standard_delivery")
    private RuleDelivery standardDelivery;
}