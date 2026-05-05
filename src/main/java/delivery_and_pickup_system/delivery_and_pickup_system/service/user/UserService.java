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

    UserRoleDto updateUserRole(int id, UserRoleDto userRoleDto);

    List<UserDto> getAllCouriers();

    UserDto getCurrentUser();

    Void deleteById(int id);

    List<UserDto> getAllActiveUsers();

    UserDto getActiveUserById(Integer id);

    void deactivateUser(Integer id);

    void activateUser(Integer id);
    

}

