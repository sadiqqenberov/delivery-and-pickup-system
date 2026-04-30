package delivery_and_pickup_system.delivery_and_pickup_system.repository;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Shipment, Integer> {
    List<Shipment> findByCreatedById(Integer customerId);

    Optional<Shipment> findByTrackingNumber(String trackingNumber);
}
