package delivery_and_pickup_system.delivery_and_pickup_system.controller;

import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.delivery.*;
import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.status.OrderStatus;
import delivery_and_pickup_system.delivery_and_pickup_system.service.delivery.DeliveryService;
import delivery_and_pickup_system.delivery_and_pickup_system.model.response.base.BaseResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delivery")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PreAuthorize("hasAnyAuthority('ADMIN','COURIER')")
    @PostMapping("/start")
    public BaseResponse<DeliveryResponseDto> start(@RequestBody StartDeliveryRequestDto dto) {

         deliveryService.startDelivery(dto);

         return (BaseResponse<DeliveryResponseDto>) BaseResponse.success(OrderStatus.IN_TRANSIT);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','COURIER')")
    @PostMapping("/confirm")
    public BaseResponse<DeliveryResponseDto> confirm(@RequestBody ConfirmDeliveryRequestDto dto) {
        deliveryService.confirmDelivery(dto);

        return (BaseResponse<DeliveryResponseDto>) BaseResponse.success(OrderStatus.DELIVERED);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','COURIER')")
    @PostMapping("/fail")
    public BaseResponse<FailedDeliveryResponseDto> fail(@RequestBody FailDeliveryRequestDto dto) {
        deliveryService.failDelivery(dto);

        return (BaseResponse<FailedDeliveryResponseDto>) BaseResponse.success(OrderStatus.DELIVERY_ATTEMPT_FAILED);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','OPERATOR')")
    @PostMapping("/reschedule")
    public DeliveryResponseDto reschedule(@RequestBody RescheduleDeliveryRequestDto dto) {
        return deliveryService.rescheduleDelivery(dto);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','OPERATOR')")
    @GetMapping("/{id}")
    public DeliveryResponseDto getById(@PathVariable Integer id) {
        return deliveryService.getDeliveryById(id);
    }

}
