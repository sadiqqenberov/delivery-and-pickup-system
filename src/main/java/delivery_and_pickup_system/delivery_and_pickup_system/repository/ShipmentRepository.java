package delivery_and_pickup_system.delivery_and_pickup_system.repository;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Integer> {
}
