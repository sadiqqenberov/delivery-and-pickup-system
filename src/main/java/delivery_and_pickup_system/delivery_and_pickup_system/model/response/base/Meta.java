package delivery_and_pickup_system.delivery_and_pickup_system.model.response.base;

import delivery_and_pickup_system.delivery_and_pickup_system.exception.BaseException;
import delivery_and_pickup_system.delivery_and_pickup_system.exception.type.NotFoundExceptionType;
import delivery_and_pickup_system.delivery_and_pickup_system.model.response.ResponseMessage;
import lombok.*;
import lombok.experimental.FieldDefaults;

import static delivery_and_pickup_system.delivery_and_pickup_system.model.enums.status.ErrorResponseMessages.NOT_FOUND;

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

    public static Meta of(ResponseMessage responseMessage) {
        return of(responseMessage.key(), responseMessage.message());
    }

    public static Meta of(BaseException ex) {
        if (ex.getResponseMessage().equals(NOT_FOUND)) {
            NotFoundExceptionType notFoundData = ex.getNotFoundData();

            return of(
                    String.format(ex.getResponseMessage().key(), notFoundData.getTarget().toLowerCase()),
                    String.format(ex.getResponseMessage().message(), notFoundData.getTarget(), notFoundData.getFields().toString())
            );
        }

        return of((ResponseMessage) ex.getResponseMessage());
    }




}
