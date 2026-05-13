package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.delivery;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class FailDeliveryRequestDto {

    @NotNull(message = "shipmentId cannot be null")
    private Integer shipmentId;

    @Size(max = 500, message = "Note cannot exceed 500 characters")
    private String note;
}