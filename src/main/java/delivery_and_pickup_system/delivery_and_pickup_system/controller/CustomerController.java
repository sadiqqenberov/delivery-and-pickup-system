package delivery_and_pickup_system.delivery_and_pickup_system.controller;

import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.shipment.ShipmentResponseDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.status_history.TrackingResponse;
import delivery_and_pickup_system.delivery_and_pickup_system.service.customer.CustomerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerController {

    final CustomerService customerService;

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping("/{id}/shipments")
    public List<ShipmentResponseDto> getCustomerShipments(@PathVariable Integer id) {

        return customerService.getCustomerShipments(id);
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping("/{trackingNumber}")
    public ResponseEntity<TrackingResponse> getTracking(@PathVariable String trackingNumber) {

        return ResponseEntity.ok(
                customerService.getTracking(trackingNumber)
        );


    }
}
