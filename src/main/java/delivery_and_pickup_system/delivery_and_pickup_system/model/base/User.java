package delivery_and_pickup_system.delivery_and_pickup_system.model.base;

import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.user.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String surname;
    String status;
    String email;
    String phoneNumber;
    String password;
    Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;


    public boolean isActive() {
        return UserStatus.ACTIVE.equals(status);
    }

}
