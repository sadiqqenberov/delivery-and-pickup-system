package delivery_and_pickup_system.delivery_and_pickup_system.service.report;

import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.report.CourierPerformanceDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.report.DeliveryReportDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.report.FailureReportDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.report.ReturnReportDto;

import java.util.List;

public interface ReportService {

    List<DeliveryReportDto> getDeliveryReports();

    List<FailureReportDto> getFailureReports();

    List<ReturnReportDto> getReturnReports();

    List<CourierPerformanceDto> getCourierPerformance();
}
