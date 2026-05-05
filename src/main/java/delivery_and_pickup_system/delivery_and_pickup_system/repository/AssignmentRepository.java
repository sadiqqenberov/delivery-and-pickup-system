package delivery_and_pickup_system.delivery_and_pickup_system.repository;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Assignment;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.assignment.AssignmentDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentRepository extends CrudRepository<Assignment, Long> {
    Optional<Assignment> findById(Integer id);

    List<Assignment> findByCourierId(Integer id);

    @Query("""
    select new delivery_and_pickup_system.delivery_and_pickup_system.model.dto.assignment.AssignmentDto(
        a.shipment.id,
        a.courier.id
    )
    from Assignment a
    where a.courier.id = :courierId
""")
    List<AssignmentDto> findAllByCourierId(@Param("courierId") Integer courierId);

}
