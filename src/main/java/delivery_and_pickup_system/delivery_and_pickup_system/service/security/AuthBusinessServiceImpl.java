package delivery_and_pickup_system.delivery_and_pickup_system.service.security;

import delivery_and_pickup_system.delivery_and_pickup_system.exception.BaseException;
import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Role;
import delivery_and_pickup_system.delivery_and_pickup_system.model.base.User;
import delivery_and_pickup_system.delivery_and_pickup_system.model.base.UserSession;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.payload.auth.RefreshTokenDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.payload.auth.LoginPayload;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.payload.auth.RefreshTokenPayload;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.payload.signup.SignUpPayload;
import delivery_and_pickup_system.delivery_and_pickup_system.model.response.auth.LoginResponse;
import delivery_and_pickup_system.delivery_and_pickup_system.model.security.LoggedInUserDetails;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.RoleRepository;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.UserRepository;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.UserSessionRepository;
import delivery_and_pickup_system.delivery_and_pickup_system.service.user.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

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
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserSessionRepository sessionRepository;


    @Override
    public LoginResponse login(LoginPayload payload) {

        authenticate(payload);

        User user = userRepository.findByEmail(payload.getEmail())
                .orElseThrow(BaseException::notFound);

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user, payload.isRememberMe());

        saveSession(user, accessToken, refreshToken);

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userInfo(LoginResponse.UserInfo.builder()
                        .id(Long.valueOf(user.getId()))
                        .name(user.getName())
                        .surname(user.getSurname())
                        .build())
                .build();
    }

    @Override
    public LoginResponse refresh(@RequestBody RefreshTokenPayload payload) {

        return prepareLoginResponse(refreshTokenManager.getEmail(payload.getRefreshToken()), payload.isRememberMe());
    }

    @Override
    @Transactional
    public void logout() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw BaseException.userNotAuthenticated();
        }

        LoggedInUserDetails userDetails = (LoggedInUserDetails) authentication.getPrincipal();

        sessionRepository.deleteAllByUserEmail(userDetails.getUsername());

        SecurityContextHolder.clearContext();
    }

    @Override
    public void setAuthentication(@RequestBody String email) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
    }

    @Override
    public User signUp(SignUpPayload payload) {

        Role role = roleRepository.findByRoleName("CUSTOMER")
                .orElseThrow(BaseException::roleNotFound);

        User user = new User();

        user.setEmail(payload.getEmail());

        user.setPassword(passwordEncoder.encode(payload.getPassword()));

        user.setName(payload.getName());
        user.setPhoneNumber(payload.getPhoneNumber());
        user.setAddress(payload.getAddress());
        user.setSurname(payload.getSurname());

        user.setRole(role);

        return userRepository.save(user);
    }


    private void authenticate(LoginPayload payload) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        payload.getEmail(),
                        payload.getPassword()
                )
        );
    }

    private LoginResponse prepareLoginResponse(String email, boolean rememberMe) {
        User user = userService.getByEmail(email);

        return LoginResponse.builder()
                .accessToken(accessTokenManager
                        .generate(user))
                .refreshToken(refreshTokenManager
                        .generate(RefreshTokenDto
                                .builder()
                                .user(user)
                                .rememberMe(rememberMe)
                                .build()))
                .build();
    }

    private void saveSession(User user, String accessToken, String refreshToken) {

        LocalDateTime now = LocalDateTime.now();

        UserSession session = UserSession.builder()
                .user(user)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .createdAtAccessToken(now)
                .createdAtRefreshToken(now)
                .accessTokenExpiresAt(now.plusMinutes(30))
                .refreshTokenExpiresAt(now.plusDays(1))
                .build();

        sessionRepository.save(session);
    }
}
