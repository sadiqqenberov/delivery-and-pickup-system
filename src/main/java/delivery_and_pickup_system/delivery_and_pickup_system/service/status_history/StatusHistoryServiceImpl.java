package delivery_and_pickup_system.delivery_and_pickup_system.service.status_history;

import delivery_and_pickup_system.delivery_and_pickup_system.exception.BaseException;
import delivery_and_pickup_system.delivery_and_pickup_system.mapper.StatusHistoryMapper;
import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Shipment;
import delivery_and_pickup_system.delivery_and_pickup_system.model.base.StatusHistory;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.status_history.StatusHistoryDTO;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.status_history.StatusUpdateRequest;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.status_history.TrackingResponse;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.ShipmentRepository;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.StatusHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatusHistoryServiceImpl implements StatusHistoryService {

    private final ShipmentRepository shipmentRepository;
    private final StatusHistoryRepository historyRepository;
    private final StatusHistoryMapper statusHistoryMapper;


    @Override
    public void updateShipmentStatus(Integer id, StatusUpdateRequest request) {
        Shipment shipment = shipmentRepository.findById(id)
                .orElseThrow(() -> BaseException.shipmentNotFound(id));

        shipment.setStatus(request.getStatus());
        shipmentRepository.save(shipment);

        StatusHistory history = StatusHistory.builder()
                .shipment(shipment)
                .status(request.getStatus())
                .changedAt(LocalDateTime.now())
                .build();

        historyRepository.save(history);
    }

    @Override
    public List<StatusHistoryDTO> getShipmentStatusHistory(Integer shipmentId) {

        if (!shipmentRepository.existsById(shipmentId)) {
            throw BaseException.shipmentNotFound(shipmentId);
        }

        List<StatusHistory> list =
                historyRepository.findAllByShipmentIdOrderByChangedAtDesc(shipmentId);

        return statusHistoryMapper.toResponseList(list);
    }

    @Override
    public TrackingResponse getTrackingInfo(String trackingNumber) {
        Shipment shipment = shipmentRepository.findByTrackingNumber(trackingNumber)
                .orElseThrow(BaseException::trackingNumberNotFound);

        List<StatusHistory> historyList = historyRepository
                .findAllByShipmentIdOrderByChangedAtDesc(shipment.getId());

        return statusHistoryMapper.toTrackingResponse(shipment, historyList);
    }

}
