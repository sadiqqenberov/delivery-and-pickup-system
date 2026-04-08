package delivery_and_pickup_system.delivery_and_pickup_system.model.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class BaseResponse<T> {
    HttpStatus status;
    Meta meta;
    T data;


    public static <T> BaseResponse<T> success(T data) {
        return null;
    }


}
