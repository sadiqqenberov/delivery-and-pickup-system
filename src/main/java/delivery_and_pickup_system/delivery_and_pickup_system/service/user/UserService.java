package delivery_and_pickup_system.delivery_and_pickup_system.service.user;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.User;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.user.UserDto;

public interface UserService {

    User getByEmail(String email);

    User creatUser(UserDto userDto);

    User findAll();
}
