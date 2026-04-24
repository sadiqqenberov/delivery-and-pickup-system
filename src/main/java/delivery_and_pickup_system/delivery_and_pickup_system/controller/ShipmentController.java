package delivery_and_pickup_system.delivery_and_pickup_system.controller;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Shipment;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.shipment.ShipmentDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.shipment.ShipmentResponseDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.status.OrderStatus;
import delivery_and_pickup_system.delivery_and_pickup_system.model.response.base.BaseResponse;
import delivery_and_pickup_system.delivery_and_pickup_system.service.shipment.ShipmentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/shipment")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShipmentController {

    final ShipmentService shipmentService;


    @PreAuthorize("hasAuthority('CUSTOMER')")
    @PostMapping("/create")
    public BaseResponse<ShipmentResponseDto> create(@RequestBody ShipmentDto shipmentDto) {

        ShipmentResponseDto response = shipmentService.createShipment(shipmentDto);

        return BaseResponse.success(OrderStatus.CREATED, response);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','OPERATOR')")
    @GetMapping("/all")
    public MappingJacksonValue findAll(){
        return shipmentService.findAll();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','OPERATOR')")
    @GetMapping("/{id}")
    public Shipment findById(@PathVariable int id) {
        return shipmentService.findById(id);
    }

    @GetMapping("/tracking/{trackingNumber}")
    public Optional<Shipment> findTracking(@PathVariable String trackintNumber) {
        return shipmentService.findByTrackingNumber(trackintNumber);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','OPERATOR')")
    @PutMapping("/update/{id}")
    public ShipmentDto update(@PathVariable int id, @RequestBody ShipmentDto shipmentDto) {
        return shipmentService.update(id,shipmentDto);
    }

    //todo: bunu yazmamisan
    @PatchMapping("/cancel/{id}")
    public BaseResponse<ShipmentDto> cancel(@PathVariable Integer id) {
        return null;
    }


    @DeleteMapping("/delete/{id}")
    public BaseResponse<?> deleted(@PathVariable Integer id) {

        shipmentService.deleteShipment(id);

        return BaseResponse.success(OrderStatus.DELETED);
    }

}
