package delivery_and_pickup_system.delivery_and_pickup_system.service.base;

public interface TokenGenerator <T>{

    String generate(T obj);

}
