package delivery_and_pickup_system.delivery_and_pickup_system.controller;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.User;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.RefreshTokenDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.payload.auth.LoginPayload;
import delivery_and_pickup_system.delivery_and_pickup_system.model.response.BaseResponse;
import delivery_and_pickup_system.delivery_and_pickup_system.model.response.auth.LoginResponse;
import delivery_and_pickup_system.delivery_and_pickup_system.service.security.AccessTokenManager;
import delivery_and_pickup_system.delivery_and_pickup_system.service.security.RefreshTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AccessTokenManager accessTokenManager;
    private final RefreshTokenManager refreshTokenManager;

    @PostMapping("/login")
    public BaseResponse<LoginResponse> login(@RequestBody LoginPayload payload){

        User user = User.builder().email("sadiqqenbervo26@gmail.com").build();
        user.setId(1);

        return BaseResponse.success(
                LoginResponse.builder()
                .accessToken(accessTokenManager.generate(user))
                .refreshToken(refreshTokenManager.generate(
                        RefreshTokenDto
                                .builder()
                                .user(user)
                                .rememberMe(payload.isRememberMe())
                                .build()
                ))
                .build()
        );
    }

}
