package delivery_and_pickup_system.delivery_and_pickup_system.service.user;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.User;

public interface UserService {

    void insert(User user);

    User getByEmail(String email);

    boolean checkByEmail(String email);
}
