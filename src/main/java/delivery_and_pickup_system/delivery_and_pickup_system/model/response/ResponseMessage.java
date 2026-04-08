package delivery_and_pickup_system.delivery_and_pickup_system.model.response;

import org.springframework.http.HttpStatus;

public interface ResponseMessage {

    String key();

    String message();

    HttpStatus status();
}
