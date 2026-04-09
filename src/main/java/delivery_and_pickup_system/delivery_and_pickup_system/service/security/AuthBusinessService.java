package delivery_and_pickup_system.delivery_and_pickup_system.service.security;

import delivery_and_pickup_system.delivery_and_pickup_system.model.payload.auth.LoginPayload;
import delivery_and_pickup_system.delivery_and_pickup_system.model.payload.auth.RefreshTokenPayload;
import delivery_and_pickup_system.delivery_and_pickup_system.model.response.auth.LoginResponse;

public interface AuthBusinessService {

    LoginResponse login(LoginPayload payload);

    LoginResponse refresh(RefreshTokenPayload payload);

    void logout();

    void setAuthentication(String email);

}
