package delivery_and_pickup_system.delivery_and_pickup_system.service.security;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.User;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.payload.auth.LoginPayload;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.payload.auth.RefreshTokenPayload;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.payload.signup.SignUpPayload;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.RefreshTokenDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.response.auth.LoginResponse;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.UserRepository;
import delivery_and_pickup_system.delivery_and_pickup_system.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthBusinessServiceImpl implements AuthBusinessService {

    private final AuthenticationManager authenticationManager;
    private final AccessTokenManager accessTokenManager;
    private final RefreshTokenManager refreshTokenManager;
    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;

    @Override
    public LoginResponse login(LoginPayload payload) {

        authenticate(payload);

        return prepareLoginResponse(payload.getEmail(), payload.isRememberMe());

    }

    @Override
    public LoginResponse refresh(@RequestBody RefreshTokenPayload payload) {

        return prepareLoginResponse(refreshTokenManager.getEmail(
                        payload.getRefreshToken()),
                payload.isRememberMe());
    }

    @Override
    public void logout() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("{} user logout succeed", userDetails.getUsername());
    }

    @Override
    public void setAuthentication(@RequestBody String email) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities())
        );
    }

    @Override
    public User signUp(@RequestBody SignUpPayload payload) {
        User user =new User();

        user.setEmail(payload.getEmail());
        user.setPassword(payload.getPassword());
        user.setName(payload.getName());
        user.setPhoneNumber(payload.getPhoneNumber());
        user.setAddress(payload.getAddress());
        user.setSurname(payload.getSurname());
//        user.setStatus(String.valueOf(payload.getStatus()));
        return userRepository.save(user);

    }

    private void authenticate(LoginPayload payload) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(payload.getEmail(), payload.getPassword()));
        } catch (AuthenticationException e) {
            throw new RuntimeException("Authentication failed", e);
        }
    }

    private LoginResponse prepareLoginResponse(String email, boolean rememberMe) {
        User user = userService.getByEmail(email);

        return LoginResponse.builder()
                .accessToken(accessTokenManager.generate(user))
                .refreshToken(refreshTokenManager.generate(
                        RefreshTokenDto.builder()
                                .user(user)
                                .rememberMe(rememberMe)
                                .build()
                ))
                .build();
    }
}
