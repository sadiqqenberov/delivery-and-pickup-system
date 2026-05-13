package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.status_history;

import delivery_and_pickup_system.delivery_and_pickup_system.model.enums.status.OrderStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StatusHistoryDTO {

    @NotNull(message = "Status cannot be null")
    private OrderStatus status;

    @NotNull(message = "ChangedAt cannot be null")
    private LocalDateTime changedAt;

    @NotBlank(message = "Note cannot be empty")
    private String note;
}