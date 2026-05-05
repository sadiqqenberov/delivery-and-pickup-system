package delivery_and_pickup_system.delivery_and_pickup_system.mapper;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Role;
import delivery_and_pickup_system.delivery_and_pickup_system.model.base.User;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.user.UserDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.user.UserRoleDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.user.UserStatusDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
)
public interface UserMapper {

    UserDto toDto(User user);

    default String map(Role role) {
        return role != null ? role.getRoleName() : null;
    }

    List<UserDto> toDtoList(List<User> users);

    UserStatusDto toDtoUser(User user);

    UserRoleDto toDtoUserRole(User user);

    @Mapping(target = "role", ignore = true)
    void updateUserFromDto(UserDto dto, @MappingTarget User user);

    void updateUserRoleFromDto(UserRoleDto dto, @MappingTarget User user);
}