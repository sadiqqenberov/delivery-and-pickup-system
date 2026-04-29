package delivery_and_pickup_system.delivery_and_pickup_system.controller;

import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.proof_of_elivery.ProofOfDeliveryRequestDTO;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.proof_of_elivery.ProofOfDeliveryResponseDTO;
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
    public ResponseEntity<ProofOfDeliveryResponseDTO> create(@RequestBody ProofOfDeliveryRequestDTO dto) {
        return ResponseEntity.ok(service.createProof(dto));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','OPERATOR','COURIER')")
    @GetMapping("/{shipmentId}")
    public ResponseEntity<ProofOfDeliveryResponseDTO> getByShipmentId(@PathVariable Integer shipmentId) {
        return ResponseEntity.ok(service.getProofByShipmentId(shipmentId));
    }
}
