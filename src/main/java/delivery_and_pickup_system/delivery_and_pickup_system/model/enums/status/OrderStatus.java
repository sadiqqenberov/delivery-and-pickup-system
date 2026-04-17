package delivery_and_pickup_system.delivery_and_pickup_system.model.enums.status;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum OrderStatus implements ResponseMessage{

    CREATED("Yaradıldı","Sifariş yaradıldı", HttpStatus.CREATED),
    REGISTERED("Qeydiyyatdan kecdi","Sifaris qeydiyyatdan kecdi", HttpStatus.OK),
    PRICE_CALCULATED("Qiymət hesablandı","Sifarisin qiyməti hesablandı", HttpStatus.OK),
    ACCEPTED("Qəbul edildi","Sifaris qəbul edildi", HttpStatus.ACCEPTED),
    PREPARED("Hazırlandı","Sifariş hazırlandı", HttpStatus.OK),
    ASSIGNED_TO_COURIER("Kuryerə təyin edildi","Sifariş kuryerə təyin edildi", HttpStatus.OK),
    PICKED_UP_BY_COURIER("Kuryer tərəfindən götürüldü","Sifariş kuryer tərəfindən götürüldü", HttpStatus.OK),
    IN_TRANSIT("Yoldadır","Sifariş çatdırılma prosesindədir", HttpStatus.OK),
    OUT_FOR_DELIVERY("Çatdırılmaya çıxıb","Sifariş çatdırılmaq üçün yoldadır", HttpStatus.OK),
    DELIVERY_ATTEMPT_FAILED("Çatdırılma uğursuz oldu","Sifariş çatdırıla bilmədi", HttpStatus.BAD_REQUEST),
    DELIVERED("Çatdırıldı","Sifariş uğurla çatdırıldı", HttpStatus.OK),
    RETURN_REQUESTED("Geri qaytarma istəyi","Sifarişin geri qaytarılması istənildi", HttpStatus.OK),
    RETURN_IN_PROGRESS("Geri qaytarılır","Sifariş geri qaytarılma prosesindədir", HttpStatus.OK),
    RETURNED("Geri qaytarıldı","Sifariş geri qaytarıldı", HttpStatus.OK),
    CANCELLED("Ləğv edildi","Sifariş ləğv edildi", HttpStatus.BAD_REQUEST),
    DAMAGED("Zədələndi","Sifariş zədələnmişdir", HttpStatus.BAD_REQUEST)
    ;

    String key;
    String message;
    HttpStatus status;


    @Override
    public String key() {
        return key;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public HttpStatus status() {
        return status;
    }
}
