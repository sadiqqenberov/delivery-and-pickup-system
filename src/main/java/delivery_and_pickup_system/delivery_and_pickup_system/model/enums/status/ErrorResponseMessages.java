package delivery_and_pickup_system.delivery_and_pickup_system.model.enums.status;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public enum ErrorResponseMessages implements ResponseMessage {
    UNEXPECTED("unexpected", "Unexpected error", HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_FOUND("not_found_%s", "%s can't find %s", HttpStatus.NOT_FOUND),
    EMAIL_ALREADY_REGISTERED("email_already_registered", "Email already registered", HttpStatus.CONFLICT),
    PHONE_NUMBER_ALREADY_EXIST("phone_number_already_exist", "Phone number already exist", HttpStatus.CONFLICT),
    FORBIDDEN("forbidden", "Forbidden", HttpStatus.FORBIDDEN),
    USER_NOT_ACTIVE("user_not_active", "User is not active", HttpStatus.FORBIDDEN),
    OTP_IS_NOT_VALID("otp_is_not_valid", "OTP is not valid", HttpStatus.CONFLICT),
    SHIPMENT_NOT_FOUND("shipment_not_found", "Shipment not found", HttpStatus.CONFLICT),
    COURIER_NOT_FOUND("courier_not_found", "Courier not found", HttpStatus.CONFLICT),
    DELIVERY_NOT_FOUND("delivery_not_found", "Delivery not found", HttpStatus.CONFLICT),
    DELIVERY_CONFIRMED("delivery_confirmed", "Delivery confirmed", HttpStatus.CONFLICT),
    RETURN_REQUEST_NOT_FOUND("return_request_not_found", "Return request not found", HttpStatus.NOT_FOUND),
    CANCELLED_RETURN_CANNOT_COMPLETED("cancelled_return_cannot_completed", "Cancelled return can not completed", HttpStatus.CONFLICT),
    ROLE_NOT_FOUND("role_not_found", "Role not found", HttpStatus.NOT_FOUND),
    TRACKING_NUMBER_NOT_FOUND("tracking_number_not_found", "Tracking number not found", HttpStatus.NOT_FOUND),
    USER_EXISTS("user_exists", "User exists", HttpStatus.CONFLICT),
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
