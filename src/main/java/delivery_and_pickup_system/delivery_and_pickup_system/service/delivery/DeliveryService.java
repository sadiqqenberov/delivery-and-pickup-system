package delivery_and_pickup_system.delivery_and_pickup_system.service.delivery;

import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.delivery.*;

public interface DeliveryService {

    DeliveryResponseDto startDelivery(StartDeliveryRequestDto dto);

    DeliveryResponseDto confirmDelivery(ConfirmDeliveryRequestDto dto);

    FailedDeliveryResponseDto failDelivery(FailDeliveryRequestDto dto);

    DeliveryResponseDto rescheduleDelivery(RescheduleDeliveryRequestDto dto);

    DeliveryResponseDto getDeliveryById(Integer id);

}
