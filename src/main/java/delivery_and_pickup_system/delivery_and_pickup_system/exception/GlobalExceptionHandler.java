package delivery_and_pickup_system.delivery_and_pickup_system.exception;

import delivery_and_pickup_system.delivery_and_pickup_system.model.response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@AllArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<BaseResponse<?>> handleException(BaseException ex) {


        return ResponseEntity.status(ex.getResponseMessage().status()).body(BaseResponse.error(ex));
    }
}
