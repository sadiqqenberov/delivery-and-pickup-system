package delivery_and_pickup_system.delivery_and_pickup_system.service.returnn;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Return;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.returnn.ReturnResponseDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.returnn.ReturnRequestDto;

public interface ReturnService {
    ReturnResponseDto initiate(ReturnRequestDto dto);

    ReturnResponseDto approve(Integer id);

    ReturnResponseDto complete(Integer id);

    Return getReturnById(Integer id);

}