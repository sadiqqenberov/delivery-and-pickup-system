    package delivery_and_pickup_system.delivery_and_pickup_system.model.base;

    import jakarta.persistence.*;
    import lombok.*;
    import lombok.experimental.FieldDefaults;

    @Entity
    @Table(name = "failed_delivery")
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Builder
    public class FailedDelivery {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Integer id;

        @ManyToOne
        @JoinColumn(name = "shipment_id")
        Shipment shipment;


        String note;
    }
