package delivery_and_pickup_system.delivery_and_pickup_system.repository;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Assignment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentRepository extends CrudRepository<Assignment, Long> {
    Optional<Assignment> findById(Integer id);

    Assignment findByCourierId(Integer id);

    List<Assignment> findAllByCourierId(Integer courierId);

}
