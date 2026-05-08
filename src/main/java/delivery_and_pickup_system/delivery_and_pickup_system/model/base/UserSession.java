package delivery_and_pickup_system.delivery_and_pickup_system.model.base;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_sessions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, unique = true, length = 1000)
    String accessToken;

    @Column(nullable = false, unique = true, length = 1000)
    String refreshToken;

    @Column(nullable = false)
    LocalDateTime createdAtAccessToken;

    @Column(nullable = false)
    LocalDateTime createdAtRefreshToken;

    @Column(nullable = false)
    LocalDateTime accessTokenExpiresAt;

    @Column(nullable = false)
    LocalDateTime refreshTokenExpiresAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    User user;
}