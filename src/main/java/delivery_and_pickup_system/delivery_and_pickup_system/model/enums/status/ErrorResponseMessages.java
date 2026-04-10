package delivery_and_pickup_system.delivery_and_pickup_system.model.enums.status;

import delivery_and_pickup_system.delivery_and_pickup_system.model.response.BaseResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public enum ErrorResponseMessages implements ResponseMessage {
    UNEXPECTED("unexpected","unexpected error",HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_FOUND("not_found_%s","%s can't find %s", HttpStatus.NOT_FOUND),

    ;

    String key;
    String message;
    HttpStatus status;

    @Override
    public String key() {
        return key;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public HttpStatus status() {
        return status;
    }
}
