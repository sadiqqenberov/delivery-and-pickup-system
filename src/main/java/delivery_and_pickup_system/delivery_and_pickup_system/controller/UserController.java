package delivery_and_pickup_system.delivery_and_pickup_system.controller;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.User;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.user.UserDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.user.UserRoleDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.user.UserStatusDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.response.base.BaseResponse;
import delivery_and_pickup_system.delivery_and_pickup_system.service.user.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserController {

    final UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/creat")
    public BaseResponse<User> createUser(@RequestBody UserDto userDto) {
        userService.creatUser(userDto);
        return BaseResponse.success();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all")
    public MappingJacksonValue findAllUsers() {
        return userService.findAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public User findUserById(@PathVariable int id) {
        return userService.findById(id);
    }

        @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update/{id}")
    public UserDto updateUser(@PathVariable int id, @RequestBody UserDto userDto) {
        return userService.update(id,userDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/status/{id}")
    public UserStatusDto updateUserStatus(@PathVariable int id, @RequestBody UserStatusDto userStatusDto) {
        return userService.updateStatus(id,userStatusDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/role/{id}")
    public UserRoleDto updateUserRole(@PathVariable int id, @RequestBody UserRoleDto userRoleDto) {
        return userService.updateUserRole(id,userRoleDto);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','OPERATOR')")
    @GetMapping("/couriers")
    public List<UserDto> getAllCouriers() {
        return userService.getAllCouriers();
    }

    @GetMapping("/me")
    public UserDto getCurrentUser() {
        return userService.getCurrentUser();
    }


}
