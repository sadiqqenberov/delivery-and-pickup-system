package delivery_and_pickup_system.delivery_and_pickup_system.service.returnn;

import delivery_and_pickup_system.delivery_and_pickup_system.exception.BaseException;
import delivery_and_pickup_system.delivery_and_pickup_system.mapper.ReturnMapper;
import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Return;
import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Shipment;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.returnn.ReturnResponseDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.returnn.ReturnRequestDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.status.OrderStatus;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.ReturnRepository;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.ShipmentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReturnServiceImpl implements ReturnService {

    ReturnRepository returnRepository;
    ShipmentRepository shipmentRepository;
    ReturnMapper returnMapper;

    @Override
    public ReturnResponseDto initiate(ReturnRequestDto dto) {

        Shipment shipment = shipmentRepository.findById(dto.getShipmentId())
                .orElseThrow(BaseException::shipmentNotFound);

        Return ret = Return.builder()
                .shipment(shipment)
                .reason(dto.getReason())
                .status(OrderStatus.RETURN_REQUESTED)
                .build();
        shipment.setStatus(OrderStatus.RETURN_REQUESTED)    ;

        return returnMapper.toDto(returnRepository.save(ret));
    }

    @Override
    public ReturnResponseDto approve(Integer id) {
        Shipment shipment = shipmentRepository.findById(id)
                .orElseThrow(BaseException::shipmentNotFound);

        Return ret = returnRepository.findById(id)
                .orElseThrow(() -> BaseException.returnRequestNotFound(id));

        if (ret.getStatus() != OrderStatus.RETURN_REQUESTED) {
            throw BaseException.cancelledReturnCannotCompleted();
        }

        ret.setStatus(OrderStatus.RETURN_IN_PROGRESS);
        shipment.setStatus(OrderStatus.RETURN_REQUESTED)    ;

        return returnMapper.toDto(returnRepository.save(ret));
    }

    @Override
    public ReturnResponseDto complete(Integer id) {

        Shipment shipment = shipmentRepository.findById(id)
                .orElseThrow(BaseException::shipmentNotFound);
        Return ret = returnRepository.findById(id)
                .orElseThrow(() -> BaseException.returnRequestNotFound(id));

        if (ret.getStatus() != OrderStatus.RETURN_IN_PROGRESS) {
            throw BaseException.cancelledReturnCannotCompleted();
        }

        ret.setStatus(OrderStatus.RETURNED);
        ret.setReturnedAt(LocalDateTime.now());
        shipment.setStatus(OrderStatus.RETURN_REQUESTED)    ;

        return returnMapper.toDto(returnRepository.save(ret));
    }

    @Override
    public Return getReturnById(Integer id) {
        return returnRepository.findById(id)
                .orElseThrow(() -> BaseException.returnRequestNotFound(id));
    }

}
