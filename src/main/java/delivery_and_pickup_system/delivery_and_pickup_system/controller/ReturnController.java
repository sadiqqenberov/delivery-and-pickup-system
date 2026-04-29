package delivery_and_pickup_system.delivery_and_pickup_system.controller;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Return;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.returnn.ReturnRequestDTO;
import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.status.OrderStatus;
import delivery_and_pickup_system.delivery_and_pickup_system.model.response.base.BaseResponse;
import delivery_and_pickup_system.delivery_and_pickup_system.service.returnn.ReturnService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/returns")
@RequiredArgsConstructor
public class ReturnController {

    private final ReturnService returnService;

    @PreAuthorize("hasAnyAuthority('ADMIN','OPERATOR','COURIER')")
    @PostMapping("/initiate")
    public BaseResponse<?> initiateReturn(@RequestBody ReturnRequestDTO requestDTO) {

        returnService.initiateReturn(requestDTO);
        return BaseResponse.successes(OrderStatus.RETURN_REQUESTED);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','OPERATOR')")
    @PostMapping("/{id}/approve")
    public BaseResponse<Return> approveReturn(@PathVariable Integer id) {
        returnService.approveReturn(id);
        return (BaseResponse<Return>) BaseResponse.successes(OrderStatus.RETURN_IN_PROGRESS);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','OPERATOR')")
    @PostMapping("/{id}/complete")
    public BaseResponse<Return> completeReturn(@PathVariable Integer id) {
        returnService.completeReturn(id);
        return (BaseResponse<Return>) BaseResponse.successes(OrderStatus.RETURNED);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','OPERATOR')")
    @GetMapping("/{id}")
    public ResponseEntity<Return> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(returnService.getReturnById(id));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','OPERATOR')")
    @GetMapping
    public ResponseEntity<List<Return>> getAll(@RequestParam(required = false) OrderStatus status) {
        return ResponseEntity.ok(returnService.getAllReturns(status));
    }
}
