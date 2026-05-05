package delivery_and_pickup_system.delivery_and_pickup_system.controller;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Shipment;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.shipment.ShipmentDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.status_history.TrackingResponse;
import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.status.OrderStatus;
import delivery_and_pickup_system.delivery_and_pickup_system.model.response.base.BaseResponse;
import delivery_and_pickup_system.delivery_and_pickup_system.service.shipment.ShipmentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/shipment")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShipmentController {

    final ShipmentService shipmentService;


    @PreAuthorize("hasAuthority('CUSTOMER')")
    @PostMapping("/create")
    public BaseResponse<TrackingResponse> create(@RequestBody ShipmentDto shipmentDto) {

        TrackingResponse response = shipmentService.createShipment(shipmentDto);

        return BaseResponse.success(OrderStatus.CREATED, response);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','OPERATOR')")
    @GetMapping("/all")
    public MappingJacksonValue findAll(){
        return shipmentService.findAll();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','OPERATOR')")
    @GetMapping("/{id}")
    public ShipmentDto findById(@PathVariable int id) {
        return shipmentService.findById(id);
    }

    @GetMapping("/tracking/{trackingNumber}")
    public ResponseEntity<TrackingResponse> findTracking(@PathVariable String trackingNumber) {
        return shipmentService.findByTrackingNumber(trackingNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','OPERATOR')")
    @PutMapping("/update/{id}")
    public ShipmentDto update(@PathVariable int id, @RequestBody ShipmentDto shipmentDto) {
        return shipmentService.update(id,shipmentDto);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','OPERATOR')")
    @PatchMapping("/{id}/cancel")
    public BaseResponse<OrderStatus> cancelShipment(@PathVariable Integer id) {

        shipmentService.cancelShipment(id);

        return BaseResponse.success(OrderStatus.CANCELLED);
    }


    @DeleteMapping("/delete/{id}")
    public void deleted(@PathVariable Integer id) {

        shipmentService.deleteShipment(id);
    }

}
