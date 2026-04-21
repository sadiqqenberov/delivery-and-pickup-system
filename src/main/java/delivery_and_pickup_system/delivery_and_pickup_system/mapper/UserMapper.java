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

    List<UserDto> toDtoList(List<User> users);

    User toEntity(UserDto dto);

    default String map(Role role) {
        return role != null ? role.getRoleName() : null;
    }

    default Role map(String roleName) {
        if (roleName == null) return null;

        Role role = new Role();
        role.setRoleName(roleName);
        return role;
    }

    UserStatusDto toDtoUser(User user);

    UserRoleDto toDtoUserRole(User user);

    void updateUserFromDto(UserDto dto, @MappingTarget User user);

    void updateUserStatusFromDto(UserStatusDto dto, @MappingTarget User user);

    void updateUserRoleFromDto(UserRoleDto dto, @MappingTarget User user);
}