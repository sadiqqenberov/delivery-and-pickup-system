package delivery_and_pickup_system.delivery_and_pickup_system.service.role;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Role;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.role.RoleDto;

public interface RoleService {

    Role createRole(RoleDto roleDto);

    void deleteRole(Long id);

    void deactivateRole(Long id);

    void activateRole(Long id);

}
