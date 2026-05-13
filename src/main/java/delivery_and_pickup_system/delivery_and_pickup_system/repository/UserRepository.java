package delivery_and_pickup_system.delivery_and_pickup_system.repository;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.User;
import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.user.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailWithRole(String email);

    User findById(int id);

    List<User> findAllByStatus(UserStatus status);

    Optional<User> findByIdAndStatus(Integer id, UserStatus status);

    @Query("SELECT u FROM User u WHERE u.role.id = :roleId")
    List<User> findAllCouriers(@Param("roleId") Long roleId);

    Optional<User> findFirstByNameAndSurname(String name, String surname);
}