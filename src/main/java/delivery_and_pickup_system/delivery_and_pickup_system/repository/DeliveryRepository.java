package delivery_and_pickup_system.delivery_and_pickup_system.repository;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Delivery;
import delivery_and_pickup_system.delivery_and_pickup_system.model.base.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {
    long countByCourierAndSuccess(User courier, Boolean success);

}
