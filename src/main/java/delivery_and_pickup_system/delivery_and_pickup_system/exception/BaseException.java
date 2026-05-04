package delivery_and_pickup_system.delivery_and_pickup_system.exception;

import com.fasterxml.jackson.databind.ser.Serializers;
import delivery_and_pickup_system.delivery_and_pickup_system.exception.type.NotFoundExceptionType;
import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.status.ErrorResponseMessages;
import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.status.ResponseMessage;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.AuthenticationException;

import java.security.PublicKey;
import java.util.Map;

import static delivery_and_pickup_system.delivery_and_pickup_system.model.enums.status.ErrorResponseMessages.*;

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

    public static BaseException notFound() {
        return of(NOT_FOUND);
    }

    public static BaseException unexpected(AuthenticationException e) {
        return of(UNEXPECTED);
    }

    public static BaseException shipmentNotFound() {
        return of(SHIPMENT_NOT_FOUND);
    }

    public static BaseException shipmentNotFound(Integer id) {
        return of(SHIPMENT_NOT_FOUND);
    }

    public static BaseException shipmentAlreadyCancelled(){
        return of(SHIPMENT_ALREADY_CANCELLED);
    }

    public static BaseException deliveredShipmentCannotCancelled(){
        return of(DELIVERED_SHIPMENT_CANNOT_CANCELLED);
    }

    public static BaseException courierNotFound(){
        return of(COURIER_NOT_FOUND);
    }

    public static BaseException deliveryNotFound() {
        return of(DELIVERY_NOT_FOUND);
    }

    public static BaseException deliveryConfirmed(){
        return of(DELIVERY_CONFIRMED);
    }

    public static BaseException returnRequestNotFound(Integer id){
        return of(RETURN_REQUEST_NOT_FOUND);
    }

    public static BaseException cancelledReturnCannotCompleted(){
        return of(CANCELLED_RETURN_CANNOT_COMPLETED);
    }

    public static BaseException roleNotFound(){
        return of(ROLE_NOT_FOUND);
    }

    public static BaseException trackingNumberNotFound(){
        return of(TRACKING_NUMBER_NOT_FOUND);
    }

    public static BaseException proofOfDeliveryNotFound(){
        return of(PROOF_OF_DELIVERY_NOT_FOUND);
    }

    public static BaseException proofOfDeliveryAlreadyExists(){
        return of(PROOF_OF_DELIVERY_ALREADY_EXISTS);
    }

    public static BaseException userExists(){
        return of(USER_EXISTS);
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
