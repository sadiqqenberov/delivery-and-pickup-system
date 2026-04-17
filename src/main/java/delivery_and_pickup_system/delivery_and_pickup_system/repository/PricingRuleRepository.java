package delivery_and_pickup_system.delivery_and_pickup_system.repository;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.PricingRule;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PricingRuleRepository extends CrudRepository<PricingRule, Long> {

    Optional<PricingRule> findFirstByStandardDelivery(String standardDelivery);

    PricingRule findById(int id);
}
