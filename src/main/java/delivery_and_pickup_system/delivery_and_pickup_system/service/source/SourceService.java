package delivery_and_pickup_system.delivery_and_pickup_system.service.source;

import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.source.SourceResponse;

public interface SourceService {

    SourceResponse getSource(Integer userId);

}
