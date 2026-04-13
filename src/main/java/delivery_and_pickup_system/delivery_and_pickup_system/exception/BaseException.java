package delivery_and_pickup_system.delivery_and_pickup_system.exception;

import delivery_and_pickup_system.delivery_and_pickup_system.exception.type.NotFoundExceptionType;
import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.status.ErrorResponseMessages;
import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.status.ResponseMessage;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Map;

import static delivery_and_pickup_system.delivery_and_pickup_system.model.enums.status.ErrorResponseMessages.NOT_FOUND;
import static delivery_and_pickup_system.delivery_and_pickup_system.model.enums.status.ErrorResponseMessages.UNEXPECTED;

@EqualsAndHashCode(callSuper=true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseException extends RuntimeException{

    ResponseMessage responseMessage;
    ErrorResponseMessages errorResponseMessages;
    NotFoundExceptionType notFoundData;

    @Override
    public String getMessage() {
        return responseMessage.message();
    }

    public static BaseException unexpected() {
        return of(UNEXPECTED);
    }
    public static BaseException of(ResponseMessage responseMessage) {
        return BaseException.builder().responseMessage(responseMessage).build();
    }

    public static BaseException notFound(String target,String field , Object value){
        return BaseException.builder()
                .responseMessage(NOT_FOUND)
                .notFoundData(
                        NotFoundExceptionType.of(target, Map.of(field,value)))
                .build();

    }


}
