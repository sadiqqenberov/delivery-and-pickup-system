package delivery_and_pickup_system.delivery_and_pickup_system.model.enums.base;

public enum OrderStatus {

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


}
