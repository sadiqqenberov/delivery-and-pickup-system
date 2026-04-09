package delivery_and_pickup_system.delivery_and_pickup_system.exception;

import lombok.*;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper=true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseException extends RuntimeException{
}
