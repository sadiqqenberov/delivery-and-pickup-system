package delivery_and_pickup_system.delivery_and_pickup_system.controller;

import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.source.SourceResponse;
import delivery_and_pickup_system.delivery_and_pickup_system.model.response.base.BaseResponse;
import delivery_and_pickup_system.delivery_and_pickup_system.service.source.SourceService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sources")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SourceController {

    SourceService sourceService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{userId}")
    public BaseResponse<SourceResponse> getSource( @PathVariable Integer userId) {
        return BaseResponse.success(
                sourceService.getSource(userId)
        );
    }
}
