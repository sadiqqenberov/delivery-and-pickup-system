package delivery_and_pickup_system.delivery_and_pickup_system.service.source;

import delivery_and_pickup_system.delivery_and_pickup_system.exception.BaseException;
import delivery_and_pickup_system.delivery_and_pickup_system.mapper.SourceMapper;
import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Assignment;
import delivery_and_pickup_system.delivery_and_pickup_system.model.base.PricingRule;
import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Shipment;
import delivery_and_pickup_system.delivery_and_pickup_system.model.base.User;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.source.CourierInfoResponse;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.source.ShipmentInfoResponse;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.source.SourceResponse;
import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.pricing_rule.RuleDelivery;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.PricingRuleRepository;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.ShipmentRepository;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.SourceRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static delivery_and_pickup_system.delivery_and_pickup_system.model.enums.status.ErrorResponseMessages.NOT_FOUND;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SourceServiceImpl implements SourceService {

    SourceRepository sourceRepository;
    ShipmentRepository shipmentRepository;
    SourceMapper sourceMapper;
    PricingRuleRepository pricingRuleRepository;

    @Override
    public SourceResponse getSource(Integer userId) {

        User user = sourceRepository.findById(userId)
                .orElseThrow(BaseException::userNotFound);

        List<Shipment> shipments =
                shipmentRepository.findAllByCreatedById(userId);

        List<ShipmentInfoResponse> shipmentResponses = new ArrayList<>();

        BigDecimal totalPrice = BigDecimal.ZERO;

        PricingRule rule = pricingRuleRepository
                .findFirstByStandardDelivery(RuleDelivery.STANDARD)
                .orElseThrow(BaseException::notFound);

        for (Shipment shipment : shipments) {

            CourierInfoResponse courierResponse = null;

            if (shipment.getAssignments() != null &&
                    !shipment.getAssignments().isEmpty()) {

                Assignment assignment = shipment.getAssignments().get(0);

                courierResponse = CourierInfoResponse.builder()
                        .id(assignment.getCourier().getId())
                        .name(assignment.getCourier().getName())
                        .surname(assignment.getCourier().getSurname())
                        .build();
            }

            BigDecimal shipmentPrice = rule.getBasePrice();

            if (shipment.getWeight().compareTo(rule.getMaxWeight()) > 0) {

                BigDecimal extraWeight =
                        shipment.getWeight().subtract(rule.getMaxWeight());

                shipmentPrice = shipmentPrice.add(
                        extraWeight.multiply(rule.getExtraPricePerKg())
                );
            }

            totalPrice = totalPrice.add(shipmentPrice);

            ShipmentInfoResponse shipmentInfo =
                    ShipmentInfoResponse.builder()
                            .trackingNumber(shipment.getTrackingNumber())
                            .weight(shipment.getWeight())
                            .price(shipmentPrice)
                            .totalPrice(shipmentPrice)
                            .status(shipment.getStatus())
                            .courier(courierResponse)
                            .build();

            shipmentResponses.add(shipmentInfo);
        }

        SourceResponse response = sourceMapper.toResponse(user);

        response.setShipments(shipmentResponses);
        response.setTotalPrice(totalPrice);

        return response;
    }
}
