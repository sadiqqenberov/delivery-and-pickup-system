package delivery_and_pickup_system.delivery_and_pickup_system.controller;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Return;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.returnn.ReturnResponseDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.returnn.ReturnRequestDto;
import delivery_and_pickup_system.delivery_and_pickup_system.service.returnn.ReturnService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/returns")
@RequiredArgsConstructor
public class ReturnController {

    private final ReturnService returnService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/initiate")
    public ReturnResponseDto initiate(@RequestBody ReturnRequestDto dto) {
        return returnService.initiate(dto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{id}/approve")
    public ReturnResponseDto approve(@PathVariable Integer id) {
        return returnService.approve(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{id}/complete")
    public ReturnResponseDto complete(@PathVariable Integer id) {
        return returnService.complete(id);
    }


    @PreAuthorize("hasAnyAuthority('ADMIN','OPERATOR')")
    @GetMapping("/{id}")
    public ResponseEntity<Return> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(returnService.getReturnById(id));
    }

}
