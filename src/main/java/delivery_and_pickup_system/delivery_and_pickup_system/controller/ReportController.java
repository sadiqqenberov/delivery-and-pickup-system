package delivery_and_pickup_system.delivery_and_pickup_system.controller;

import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.report.CourierPerformanceDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.report.DeliveryReportDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.report.FailureReportDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.report.ReturnReportDto;
import delivery_and_pickup_system.delivery_and_pickup_system.service.report.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping("/deliveries")
    public ResponseEntity<List<DeliveryReportDto>> getDeliveries() {
        return ResponseEntity.ok(reportService.getDeliveryReports());
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping("/failures")
    public ResponseEntity<List<FailureReportDto>> getFailures() {
        return ResponseEntity.ok(reportService.getFailureReports());
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping("/returns")
    public ResponseEntity<List<ReturnReportDto>> getReturns() {
        return ResponseEntity.ok(reportService.getReturnReports());
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping("/couriers/performance")
    public ResponseEntity<List<CourierPerformanceDto>> getCourierPerformance() {
        return ResponseEntity.ok(reportService.getCourierPerformance());
    }
}
