package delivery_and_pickup_system.delivery_and_pickup_system.repository;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Shipment;
import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.status.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Integer> {

    Optional<Shipment> findByTrackingNumber(String trackingNumber);

    Shipment findById(int id);

    Shipment deleteById(int id);

    long countByStatus(OrderStatus status);
}
