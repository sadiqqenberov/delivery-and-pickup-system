package delivery_and_pickup_system.delivery_and_pickup_system.controller;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Role;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.role.RoleDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.response.base.BaseResponse;
import delivery_and_pickup_system.delivery_and_pickup_system.service.role.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleController {

    final RoleService roleService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/creat")
    public BaseResponse<Role> createRole(@RequestBody RoleDto roleDto) {
        roleService.createRole(roleDto);
        return BaseResponse.success();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public BaseResponse<Void> deleteRole(@PathVariable Long id ) {
        roleService.deleteRole(id);
        return BaseResponse.success();
    }

}
