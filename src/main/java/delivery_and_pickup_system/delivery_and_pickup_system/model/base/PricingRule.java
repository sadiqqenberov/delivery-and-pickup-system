package delivery_and_pickup_system.delivery_and_pickup_system.model.base;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "pricing_rules")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class PricingRule  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double minWeight;
    private Double maxWeight;

    private Double basePrice;

    private Double pricePerKm;

    private Boolean isExpress;

    private Double extraCharge;


}