package delivery_and_pickup_system.delivery_and_pickup_system.model.response.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import delivery_and_pickup_system.delivery_and_pickup_system.exception.BaseException;
import lombok.*;
import org.springframework.http.HttpStatus;

import static delivery_and_pickup_system.delivery_and_pickup_system.model.response.SuccessResponseMessages.SUCCESS;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {
    HttpStatus status;
    Meta meta;
    T data;


    public static <T> BaseResponse<T> success(T data) {
        return BaseResponse.<T>builder()
                .status(HttpStatus.OK)
                .data(data)
                .meta(Meta.of(SUCCESS))
                .build();
    }

    public static <T> BaseResponse<T> success() {
        return success(null);
    }

    public static BaseResponse<?> error(BaseException ex) {
        return BaseResponse.builder()
                .meta(Meta.of(ex))
                .status(ex.getResponseMessage().status())
                .build();
    }


}
