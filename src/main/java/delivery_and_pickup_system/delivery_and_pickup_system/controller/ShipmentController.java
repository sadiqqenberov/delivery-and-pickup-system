package delivery_and_pickup_system.delivery_and_pickup_system.controller;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Shipment;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.shipment.ShipmentDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.response.base.BaseResponse;
import delivery_and_pickup_system.delivery_and_pickup_system.service.shipment.ShipmentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/shipment")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShipmentController {

    final ShipmentService shipmentService;

    @PostMapping("/creat")
    public BaseResponse<Shipment> create(@RequestBody ShipmentDto shipmentDto) {
        shipmentService.createShipment(shipmentDto);
        return BaseResponse.success();
    }

    @GetMapping("/all")
    public MappingJacksonValue findAll(){
        return shipmentService.findAll();
    }

    @GetMapping("/{id}")
    public Shipment findById(@PathVariable int id) {
        return shipmentService.findById(id);
    }

    @GetMapping("/tracking/{trackingNumber}")
    public Optional<Shipment> findTracking(@PathVariable Integer trackintNumber) {
        return shipmentService.findByTrackingNumber(trackintNumber);
    }

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
    public Void delete(@PathVariable Integer id) {
        return shipmentService.deleteShipment(id);
    }

}
