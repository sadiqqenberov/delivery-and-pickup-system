package delivery_and_pickup_system.delivery_and_pickup_system.service.returnn;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Return;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.returnn.ReturnRequestDTO;
import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.status.OrderStatus;

import java.util.List;

public interface ReturnService {
    Return initiateReturn(ReturnRequestDTO requestDTO);

    Return approveReturn(Integer id);

    Return completeReturn(Integer id);

    Return getReturnById(Integer id);

    List<Return> getAllReturns(OrderStatus status);
}