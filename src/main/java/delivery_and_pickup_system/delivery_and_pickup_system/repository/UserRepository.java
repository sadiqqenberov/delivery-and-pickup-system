package delivery_and_pickup_system.delivery_and_pickup_system.repository;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(@Param("email") String email);

}
