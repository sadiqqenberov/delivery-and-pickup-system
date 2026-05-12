package delivery_and_pickup_system.delivery_and_pickup_system.service.role;

import delivery_and_pickup_system.delivery_and_pickup_system.exception.BaseException;
import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Role;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.role.RoleDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.role.RoleStatus;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements  RoleService{

    private final RoleRepository roleRepository;

    @Override
    public Role createRole(@RequestBody RoleDto roleDto) {
        Role role = new Role();
        role.setRoleName(roleDto.getRoleName());
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public void deactivateRole(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(BaseException::roleNotFound);

        role.setStatus(RoleStatus.DEACTIVATED);
        roleRepository.save(role);
    }

    @Override
    public void activateRole(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(BaseException::roleNotFound);
        role.setStatus(RoleStatus.ACTIVATED);
        roleRepository.save(role);
    }

}
