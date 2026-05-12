package delivery_and_pickup_system.delivery_and_pickup_system.service.returnn;

import delivery_and_pickup_system.delivery_and_pickup_system.exception.BaseException;
import delivery_and_pickup_system.delivery_and_pickup_system.mapper.ReturnMapper;
import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Return;
import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Shipment;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.returnn.ReturnRequestDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.returnn.ReturnResponseDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.status.OrderStatus;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.ReturnRepository;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.ShipmentRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    @Override
    public ReturnResponseDto initiate(ReturnRequestDto dto) {

        Shipment shipment = shipmentRepository.findById(dto.getShipmentId())
                .orElseThrow(BaseException::shipmentNotFound);

        Return ret = Return.builder()
                .shipment(shipment)
                .reason(dto.getReason())
                .status(OrderStatus.RETURN_REQUESTED)
                .build();

        ret.setStatus(OrderStatus.RETURN_REQUESTED);
        shipment.setStatus(OrderStatus.RETURN_REQUESTED);
        shipmentRepository.save(shipment);

        return returnMapper.toDto(returnRepository.save(ret));
    }

    @Transactional
    @Override
    public ReturnResponseDto approve(Integer id) {

        Return ret = returnRepository.findById(id)
                .orElseThrow(() -> BaseException.returnRequestNotFound(id));

        Shipment shipment = shipmentRepository.findById(ret.getShipment().getId())
                .orElseThrow(BaseException::shipmentNotFound);

        if (ret.getStatus() != OrderStatus.RETURN_REQUESTED) {
            throw BaseException.cancelledReturnCannotCompleted();
        }

        ret.setStatus(OrderStatus.RETURN_IN_PROGRESS);
        shipment.setStatus(OrderStatus.RETURN_IN_PROGRESS);
        shipmentRepository.save(shipment);

        return returnMapper.toDto(returnRepository.save(ret));
    }

    @Transactional
    @Override
    public ReturnResponseDto complete(Integer id) {

        Return ret = returnRepository.findById(id)
                .orElseThrow(() -> BaseException.returnRequestNotFound(id));

        Shipment shipment = shipmentRepository.findById(ret.getShipment().getId())
                .orElseThrow(BaseException::shipmentNotFound);

        if (ret.getStatus() != OrderStatus.RETURN_IN_PROGRESS) {
            throw BaseException.cancelledReturnCannotCompleted();
        }

        ret.setStatus(OrderStatus.RETURNED);
        ret.setReturnedAt(LocalDateTime.now());

        shipment.setStatus(OrderStatus.RETURNED);
        shipmentRepository.save(shipment);

        return returnMapper.toDto(returnRepository.save(ret));
    }

    @Override
    public Return getReturnById(Integer id) {
        return returnRepository.findById(id)
                .orElseThrow(() -> BaseException.returnRequestNotFound(id));
    }
}