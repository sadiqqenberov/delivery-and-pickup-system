package delivery_and_pickup_system.delivery_and_pickup_system.controller;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.User;
import delivery_and_pickup_system.delivery_and_pickup_system.model.payload.auth.LoginPayload;
import delivery_and_pickup_system.delivery_and_pickup_system.model.payload.auth.RefreshTokenPayload;
import delivery_and_pickup_system.delivery_and_pickup_system.model.payload.signup.SignUpPayload;
import delivery_and_pickup_system.delivery_and_pickup_system.model.response.base.BaseResponse;
import delivery_and_pickup_system.delivery_and_pickup_system.model.response.auth.LoginResponse;
import delivery_and_pickup_system.delivery_and_pickup_system.service.security.AuthBusinessService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthController {

    private final AuthBusinessService authBusinessService;


    @PostMapping("/login")
    public BaseResponse<LoginResponse> login(@RequestBody LoginPayload payload) {
        return BaseResponse.success(authBusinessService.login(payload));
    }

    @PostMapping("/token/refresh")
    public BaseResponse<LoginResponse> refresh(@RequestBody RefreshTokenPayload payload){
        return BaseResponse.success(authBusinessService.refresh(payload));
    }

    @PostMapping("/logout")
    public BaseResponse<Void> logout() {
        authBusinessService.logout();
        return BaseResponse.success();
    }

    @PostMapping("/sign-up")
    public User sigUp(@RequestBody SignUpPayload payload) {
        return authBusinessService.signUp(payload);
    }

    @GetMapping("/me")
    public BaseResponse<User> getCurrentUser() {
        return null;
    }
}
