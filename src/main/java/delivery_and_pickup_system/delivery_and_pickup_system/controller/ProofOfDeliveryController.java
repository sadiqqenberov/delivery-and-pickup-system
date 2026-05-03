package delivery_and_pickup_system.delivery_and_pickup_system.controller;

import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.proof_of_elivery.ProofOfDeliveryRequestDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.proof_of_elivery.ProofOfDeliveryResponseDto;
import delivery_and_pickup_system.delivery_and_pickup_system.service.proof_of_delivery.ProofOfDeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/proof-of-delivery")
@RequiredArgsConstructor
public class ProofOfDeliveryController {

    private final ProofOfDeliveryService service;

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @PostMapping
    public ResponseEntity<ProofOfDeliveryResponseDto> create(@RequestBody ProofOfDeliveryRequestDto request) {
        return ResponseEntity.ok(service.create(request));
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping("/{shipmentId}")
    public ResponseEntity<ProofOfDeliveryResponseDto> getByShipmentId(@PathVariable Integer shipmentId) {
        return ResponseEntity.ok(service.getByShipmentId(shipmentId));
    }
}
