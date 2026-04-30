package delivery_and_pickup_system.delivery_and_pickup_system.controller;

import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.status_history.StatusHistoryDTO;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.status_history.StatusUpdateRequest;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.status_history.TrackingResponse;
import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.status.OrderStatus;
import delivery_and_pickup_system.delivery_and_pickup_system.model.response.base.BaseResponse;
import delivery_and_pickup_system.delivery_and_pickup_system.service.status_history.StatusHistoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/status_history")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatusHistoryController {

    private final StatusHistoryService statusHistoryService;

    @PreAuthorize("hasAnyAuthority('ADMIN','OPERATOR','COURIER')")
    @PatchMapping("/{id}/status")
    public BaseResponse<?> updateStatus(@PathVariable Integer id, @RequestBody StatusUpdateRequest request) {
        statusHistoryService.updateShipmentStatus(id, request);
        return BaseResponse.success(OrderStatus.UPDATE_STATUS);
    }

    @GetMapping("/shipments/{id}/status-history")
    public List<StatusHistoryDTO> getStatusHistory(@PathVariable Integer id) {
        return statusHistoryService.getShipmentStatusHistory(id);
    }

    @PreAuthorize("hasAnyAuthority('CUSTOMER','OPERATOR')")
    @GetMapping("/{trackingNumber}")
    public TrackingResponse getTrackingDetails(@PathVariable String trackingNumber) {
        return statusHistoryService.getTrackingInfo(trackingNumber);
    }

    //todo: bunun mentiqini basa dusmemisem nedi ne deyil
    //@PostMapping("/tracking/events")
}