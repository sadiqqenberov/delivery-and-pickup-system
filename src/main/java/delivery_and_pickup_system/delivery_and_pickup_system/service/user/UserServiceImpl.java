package delivery_and_pickup_system.delivery_and_pickup_system.service.user;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.User;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public void insert(User user) {
        userRepository.insert(user);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
