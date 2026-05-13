package delivery_and_pickup_system.delivery_and_pickup_system.controller;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.User;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.user.UserDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.user.UserPasswordDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.user.UserRoleDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.status.OrderStatus;
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
    @PutMapping("/update/{id}")
    public UserDto updateUser(@PathVariable int id, @RequestBody UserDto userDto) {
        return userService.update(id,userDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/role/{id}")
    public UserRoleDto updateUserRole(@PathVariable int id, @RequestBody UserRoleDto userRoleDto) {
        return userService.updateUserRole(id,userRoleDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/password/{id}")
    public UserPasswordDto updateUserPassword(@PathVariable int id, @RequestBody UserPasswordDto userPasswordDto){
        return userService.updateUserPassword(id,userPasswordDto);
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

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public Void deleteUser(@PathVariable int id) {
        return userService.deleteById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','OPERATOR')")
    @GetMapping("/active")
    public List<UserDto> getAll() {
        return userService.getAllActiveUsers();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','OPERATOR')")
    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Integer id) {
        return userService.getActiveUserById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/{id}/deactivate")
    public BaseResponse<?> deactivate(@PathVariable Integer id) {
        userService.deactivateUser(id);
        return BaseResponse.successes(OrderStatus.DEACTIVATED);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/{id}/activate")
    public BaseResponse<?> activate(@PathVariable Integer id) {
        userService.activateUser(id);
        return BaseResponse.successes(OrderStatus.ACTIVATED);
    }

}
