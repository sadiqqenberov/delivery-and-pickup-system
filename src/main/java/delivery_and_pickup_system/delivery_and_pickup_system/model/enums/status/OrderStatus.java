package delivery_and_pickup_system.delivery_and_pickup_system.model.enums.status;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
public enum OrderStatus implements ResponseMessage{

    CREATED,
    REGISTERED,
    PRICE_CALCULATED,
    ACCEPTED,
    PREPARED,
    ASSIGNED_TO_COURIER,
    PICKED_UP_BY_COURIER,
    IN_TRANSIT,
    OUT_FOR_DELIVERY,
    DELIVERY_ATTEMPT_FAILED,
    DELIVERED,
    RETURN_REQUESTED,
    RETURN_IN_PROGRESS,
    RETURNED,
    CANCELLED,
    DAMAGED
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
