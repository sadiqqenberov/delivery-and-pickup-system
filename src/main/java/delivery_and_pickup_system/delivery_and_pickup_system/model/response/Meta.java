package delivery_and_pickup_system.delivery_and_pickup_system.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class Meta {

    String key;
    String message;

    public static Meta of(String key, String message) {
        return Meta.builder()
                .key(key)
                .message(message)
                .build();
    }

}
