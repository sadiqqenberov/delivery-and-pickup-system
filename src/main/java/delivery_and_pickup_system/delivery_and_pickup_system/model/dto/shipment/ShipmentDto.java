package delivery_and_pickup_system.delivery_and_pickup_system.model.dto.shipment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ShipmentDto {

    @NotBlank(message = "Sender name cannot be empty")
    @Size(min = 2, max = 100, message = "Sender name must be between 2 and 100 characters")
    private String senderName;

    @NotBlank(message = "Sender phone cannot be empty")
    @Size(min = 7, max = 20, message = "Sender phone must be between 7 and 20 characters")
    private String senderPhone;

    @NotBlank(message = "Receiver name cannot be empty")
    @Size(min = 2, max = 100, message = "Receiver name must be between 2 and 100 characters")
    private String receiverName;

    @NotBlank(message = "Receiver phone cannot be empty")
    @Size(min = 7, max = 20, message = "Receiver phone must be between 7 and 20 characters")
    private String receiverPhone;

    @NotBlank(message = "Delivery address cannot be empty")
    @Size(min = 5, max = 255, message = "Delivery address must be between 5 and 255 characters")
    private String deliveryAddress;

    @NotNull(message = "Weight cannot be null")
    private BigDecimal weight;

    private BigDecimal price;

    @NotBlank(message = "CreatedBy name cannot be empty")
    private String createdByName;

    @NotBlank(message = "CreatedBy surname cannot be empty")
    private String createdBySurname;
}