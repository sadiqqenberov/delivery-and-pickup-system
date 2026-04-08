package delivery_and_pickup_system.delivery_and_pickup_system.service.base;

public interface TokenReader <T>{

    T read(String token);

}
