package delivery_and_pickup_system.delivery_and_pickup_system.service.returnn;

import delivery_and_pickup_system.delivery_and_pickup_system.exception.BaseException;
import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Return;
import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Shipment;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.returnn.ReturnRequestDTO;
import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.status.OrderStatus;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.ReturnRepository;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.ShipmentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReturnServiceImpl implements ReturnService {

    ReturnRepository returnRepository;
    ShipmentRepository shipmentRepository;

    @Override
    public Return initiateReturn(ReturnRequestDTO requestDTO) {
        Shipment shipment = shipmentRepository.findById(requestDTO.getShipmentId())
                .orElseThrow(() -> BaseException.shipmentNotFound());

        Return newReturn = Return.builder()
                .shipment(shipment)
                .reason(requestDTO.getReason())
                .status(OrderStatus.RETURN_REQUESTED)
                .returnedAt(LocalDateTime.now())
                .build();

        return returnRepository.save(newReturn);
    }

    @Override
    public Return approveReturn(Integer id) {
        Return returnOrder = returnRepository.findById(id)
                .orElseThrow(() -> BaseException.returnRequestNotFound(id));

        returnOrder.setStatus(OrderStatus.RETURNED);

        if (returnOrder.getShipment() != null) {
            returnOrder.getShipment().setStatus(OrderStatus.RETURN_IN_PROGRESS);
        }

        return returnRepository.save(returnOrder);
    }

    @Override
    public Return completeReturn(Integer id) {
        Return returnOrder = returnRepository.findById(id)
                .orElseThrow(() -> BaseException.returnRequestNotFound(id));

        if (returnOrder.getStatus() == OrderStatus.CANCELLED) {
            throw BaseException.cancelledReturnCannotCompleted();
        }

        returnOrder.setStatus(OrderStatus.COMPLETED);

        if (returnOrder.getShipment() != null) {
            returnOrder.getShipment().setStatus(OrderStatus.COMPLETED);
        }

        returnOrder.setReturnedAt(LocalDateTime.now());

        return returnRepository.save(returnOrder);
    }

    @Override
    public Return getReturnById(Integer id) {
        return returnRepository.findById(id)
                .orElseThrow(() -> BaseException.returnRequestNotFound(id));
    }

    @Override
    public List<Return> getAllReturns(OrderStatus status) {
        if (status != null) {
            return returnRepository.findAllByStatus(status);
        }
        return returnRepository.findAll();
    }
}
