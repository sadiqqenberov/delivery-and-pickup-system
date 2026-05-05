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
import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.user.UserStatus;
import delivery_and_pickup_system.delivery_and_pickup_system.model.security.LoggedInUserDetails;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.RoleRepository;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static delivery_and_pickup_system.delivery_and_pickup_system.constans.TokenConstants.EMAIL_KEY;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmailWithRole(email)
                .orElseThrow(() ->
                        BaseException.notFound(User.class.getSimpleName(), EMAIL_KEY, email)
                );
    }

    @Override
    public User creatUser(UserDto userDto) {

        Role role = roleRepository.findByRoleName(userDto.getRole())
                .orElseThrow(BaseException::roleNotFound);

        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw BaseException.userExists();
        }

        User user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setAddress(userDto.getAddress());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
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

        User user = findById(id);

        userMapper.updateUserFromDto(userDto, user);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        if (userDto.getRole() != null) {
            Role role = roleRepository.findByRoleName(userDto.getRole())
                    .orElseThrow(BaseException::roleNotFound);

            user.setRole(role);
        }

        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserRoleDto updateUserRole(int id, UserRoleDto userRoleDto) {
        User user = findById(id);

        userMapper.updateUserRoleFromDto(userRoleDto, user);

        return userMapper.toDtoUserRole(userRepository.save(user));
    }

    @Override
    public List<UserDto> getAllCouriers() {
        List<User> couriers = userRepository.findAllCouriers(3L);
        return userMapper.toDtoList(couriers);
    }

    @Override
    public UserDto getCurrentUser() {

        LoggedInUserDetails userDetails =
                (LoggedInUserDetails) SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getPrincipal();

        String email = userDetails.getUsername();

        User user = userRepository.findByEmailWithRole(email)
                .orElseThrow(() ->
                        BaseException.notFound(User.class.getSimpleName(), EMAIL_KEY, email)
                );

        return userMapper.toDto(user);
    }

    @Override
    public Void deleteById(int id) {
        userRepository.deleteById(id);
        return null;
    }

    @Override
    public List<UserDto> getAllActiveUsers() {
        return userRepository.findAllByStatus(UserStatus.ACTIVE)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public UserDto getActiveUserById(Integer id) {
        User user = userRepository.findByIdAndStatus(id, UserStatus.ACTIVE)
                .orElseThrow(BaseException::userNotFound);

        return userMapper.toDto(user);
    }

    @Override
    public void deactivateUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(BaseException::userNotFound);

        user.setStatus(UserStatus.INACTIVE);
        userRepository.save(user);
    }

    @Override
    public void activateUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(BaseException::userNotFound);

        user.setStatus(UserStatus.ACTIVE);
        userRepository.save(user);
    }
}