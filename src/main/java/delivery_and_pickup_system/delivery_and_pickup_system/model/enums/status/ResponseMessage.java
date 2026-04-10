package delivery_and_pickup_system.delivery_and_pickup_system.model.enums.status;

import org.springframework.http.HttpStatus;

public interface ResponseMessage {

    String key();

    String message();

    HttpStatus status();
}
