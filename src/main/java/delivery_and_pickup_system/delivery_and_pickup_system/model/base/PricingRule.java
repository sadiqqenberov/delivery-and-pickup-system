package delivery_and_pickup_system.delivery_and_pickup_system.model.base;

import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.pricing_rule.RuleDelivery;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

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
    BigDecimal maxWeight;

    @Column(nullable = false)
    BigDecimal maxDistance;

    @Column(nullable = false)
    BigDecimal basePrice;

    @Column(nullable = false)
    BigDecimal extraPricePerKg;

    @Column(nullable = false)
    BigDecimal extraPricePerKm;

    @Enumerated(EnumType.STRING)
    @Column(name = "standard_delivery")
    RuleDelivery standardDelivery;
}