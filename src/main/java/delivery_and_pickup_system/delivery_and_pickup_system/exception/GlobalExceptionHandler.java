package delivery_and_pickup_system.delivery_and_pickup_system.exception;

import delivery_and_pickup_system.delivery_and_pickup_system.model.response.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@AllArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<BaseResponse<?>> handleException(BaseException ex) {

        return ResponseEntity.status(ex.getResponseMessage().status()).body(BaseResponse.error(ex));
    }

}