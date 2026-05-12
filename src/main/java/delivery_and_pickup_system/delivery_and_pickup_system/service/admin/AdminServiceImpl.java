package delivery_and_pickup_system.delivery_and_pickup_system.service.admin;

import delivery_and_pickup_system.delivery_and_pickup_system.mapper.DashboardMapper;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.admin.DashboardResponseDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.status.OrderStatus;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.ShipmentRepository;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final ShipmentRepository shipmentRepository;
    private final DashboardMapper dashboardMapper;

    @Override
    public DashboardResponseDto getDashboardStats() {

        long totalUsers = userRepository.count();
        long totalShipments = shipmentRepository.count();

        long delivered = shipmentRepository.countByStatus(OrderStatus.DELIVERED);
        long cancelled = shipmentRepository.countByStatus(OrderStatus.CANCELLED);

        return dashboardMapper.toDto(
                totalUsers,
                totalShipments,
                delivered,
                cancelled
        );
    }
}