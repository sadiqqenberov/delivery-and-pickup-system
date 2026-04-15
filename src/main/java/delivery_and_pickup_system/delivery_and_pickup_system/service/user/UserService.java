package delivery_and_pickup_system.delivery_and_pickup_system.service.user;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.User;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.user.UserDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.user.UserRoleDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.user.UserStatusDto;
import org.springframework.http.converter.json.MappingJacksonValue;

import java.util.List;


public interface UserService {

    User getByEmail(String email);

    User creatUser(UserDto userDto);

    MappingJacksonValue findAll();

    User findById(int id);

    UserDto update(int id, UserDto userDto);

    UserStatusDto updateStatus(int id, UserStatusDto userStatusDto);

    UserRoleDto updateUserRole(int id, UserRoleDto userRoleDto);

    List<UserDto> getAllCouriers();

}

