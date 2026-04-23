package delivery_and_pickup_system.delivery_and_pickup_system.repository;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Shipment;
import delivery_and_pickup_system.delivery_and_pickup_system.model.base.StatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface StatusHistoryRepository extends JpaRepository<StatusHistory, Integer> {

    List<StatusHistory> findAllByShipmentIdOrderByChangedAtDesc(Integer shipmentId);

//    Optional<Shipment> findByTrackingNumber(Integer trackingNumber);
}
