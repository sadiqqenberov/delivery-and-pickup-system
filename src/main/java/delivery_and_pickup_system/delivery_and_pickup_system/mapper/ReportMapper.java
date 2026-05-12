package delivery_and_pickup_system.delivery_and_pickup_system.mapper;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Delivery;
import delivery_and_pickup_system.delivery_and_pickup_system.model.base.FailedDelivery;
import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Return;
import delivery_and_pickup_system.delivery_and_pickup_system.model.base.User;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.report.CourierPerformanceDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.report.DeliveryReportDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.report.FailureReportDto;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.report.ReturnReportDto;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
)
public interface ReportMapper {

    @Mapping(target = "trackingNumber", source = "shipment.trackingNumber")
    @Mapping(target = "courierName", source = "courier.name")
    DeliveryReportDto toDeliveryDto(Delivery delivery);

    @Mapping(target = "trackingNumber", source = "shipment.trackingNumber")
    FailureReportDto toFailureDto(FailedDelivery failedDelivery);

    @Mapping(target = "trackingNumber", source = "shipment.trackingNumber")
    ReturnReportDto toReturnDto(Return returnEntity);

    @Mapping(target = "courierId", source = "user.id")
    @Mapping(target = "courierName", source = "user.name")
    @Mapping(target = "successfulDeliveries", source = "success")
    @Mapping(target = "failedDeliveries", source = "failed")
    CourierPerformanceDto toDto(User user, long success, long failed);
}