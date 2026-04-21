package delivery_and_pickup_system.delivery_and_pickup_system.service.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import delivery_and_pickup_system.delivery_and_pickup_system.exception.BaseException;
import delivery_and_pickup_system.delivery_and_pickup_system.mapper.UserMapper;
import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Role;
import delivery_and_pickup_system.delivery_and_pickup_system.model.base.User;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.user.UserDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.user.UserRoleDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.user.UserStatusDto;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.RoleRepository;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> BaseException.notFound(User.class.getSimpleName(), "email", email));
    }

    @Override
    public User creatUser(UserDto userDto) {

        Role role = roleRepository.findByRoleName(userDto.getRole())
                .orElseThrow(() -> new RuntimeException("Role tapılmadı: " + userDto.getRole()));

        User user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setPassword(userDto.getPassword());
        user.setAddress(userDto.getAddress());
        user.setRole(role);

        return userRepository.save(user);
    }

    @Override
    public MappingJacksonValue findAll() {
        List<User> users = userRepository.findAll();

        List<UserDto> userDtos = userMapper.toDtoList(users);

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "surname", "phoneNumber", "address");

        FilterProvider provider = new SimpleFilterProvider().addFilter("users", filter);

        MappingJacksonValue value = new MappingJacksonValue(userDtos);
        value.setFilters(provider);

        return value;

    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public UserDto update(int id, UserDto userDto) {
        User user = userRepository.findById(id);

        userMapper.updateUserFromDto(userDto, user);

        User userUpdated = userRepository.save(user);
        return userMapper.toDto(userUpdated);
    }

    @Override
    public UserStatusDto updateStatus(int id, UserStatusDto userStatusDto) {
        User user = userRepository.findById(id);

        userMapper.updateUserStatusFromDto(userStatusDto, user);

        User userUpdated = userRepository.save(user);

        return userMapper.toDtoUser(userUpdated);

    }

    @Override
    public UserRoleDto updateUserRole(int id, UserRoleDto userRoleDto) {
        User user = userRepository.findById(id);

        userMapper.updateUserRoleFromDto(userRoleDto, user);

        User userUpdated = userRepository.save(user);

        return userMapper.toDtoUserRole(userUpdated);
    }

    @Override
    public List<UserDto> getAllCouriers() {
        List<User> couriers = userRepository.findAllCouriers(3L);
        return userMapper.toDtoList(couriers);
    }


}
