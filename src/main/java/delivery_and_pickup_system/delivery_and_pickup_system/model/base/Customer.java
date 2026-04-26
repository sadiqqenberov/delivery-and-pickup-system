package delivery_and_pickup_system.delivery_and_pickup_system.model.base;

import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.user.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@Entity
@Table(name = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;

    String surname;

    @Column(unique = true, nullable = false)
    String email;

    String phoneNumber;

    String address;

    @Enumerated(EnumType.STRING)
    UserStatus status = UserStatus.ACTIVE;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Shipment> shipments;
}
