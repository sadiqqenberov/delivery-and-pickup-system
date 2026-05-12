package delivery_and_pickup_system.delivery_and_pickup_system.service.report;

import delivery_and_pickup_system.delivery_and_pickup_system.mapper.ReportMapper;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.report.CourierPerformanceDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.report.DeliveryReportDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.report.FailureReportDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.report.ReturnReportDto;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.DeliveryRepository;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.FailedDeliveryRepository;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.ReturnRepository;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final DeliveryRepository deliveryRepository;
    private final FailedDeliveryRepository failedDeliveryRepository;
    private final ReturnRepository returnRepository;
    private final UserRepository userRepository;
    private final ReportMapper reportMapper;

    @Override
    public List<DeliveryReportDto> getDeliveryReports() {

        return deliveryRepository.findAll()
                .stream()
                .map(reportMapper::toDeliveryDto)
                .toList();
    }

    @Override
    public List<FailureReportDto> getFailureReports() {

        return failedDeliveryRepository.findAll()
                .stream()
                .map(reportMapper::toFailureDto)
                .toList();
    }

    @Override
    public List<ReturnReportDto> getReturnReports() {

        return returnRepository.findAll()
                .stream()
                .map(reportMapper::toReturnDto)
                .toList();
    }

    @Override
    public List<CourierPerformanceDto> getCourierPerformance() {

        return userRepository.findAll()
                .stream()
                .map(user -> {

                    long success = deliveryRepository.countByCourierAndSuccess(user, true);
                    long failed = deliveryRepository.countByCourierAndSuccess(user, false);

                    return reportMapper.toDto(user, success, failed);
                })
                .toList();
    }
}