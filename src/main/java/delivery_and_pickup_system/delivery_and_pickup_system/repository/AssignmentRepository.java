package delivery_and_pickup_system.delivery_and_pickup_system.repository;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Assignment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepository extends CrudRepository<Assignment, Long> {
}
