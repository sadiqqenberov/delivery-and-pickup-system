package delivery_and_pickup_system.delivery_and_pickup_system;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class DeliveryAndPickupSystemApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DeliveryAndPickupSystemApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}


//    @Override
//    public void run(String... args) throws Exception {
//KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
//        keyGenerator.initialize(2048);
//        KeyPair kp = keyGenerator.genKeyPair();
//        PublicKey publicKey = kp.getPublic();
//        PrivateKey privateKey = kp.getPrivate();
//
//        String encodedPublicKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());
//        String encodedPrivateKey = Base64.getEncoder().encodeToString(privateKey.getEncoded());
//
//        System.out.println(convertToPublicKey(encodedPublicKey));
//
//        System.out.println();
//
//        System.out.println(convertToPrivateKey(encodedPrivateKey));
//
//    }
//
//    private static String convertToPrivateKey(String key) {
//        StringBuilder result = new StringBuilder();
//        result.append("-----BEGIN PRIVATE KEY-----\n");
//        result.append(key);
//        result.append("\n-----END PRIVATE KEY-----");
//        return result.toString();
//    }
//
//    private static String convertToPublicKey(String key) {
//        StringBuilder result = new StringBuilder();
//        result.append("-----BEGIN PUBLIC KEY-----\n");
//        result.append(key);
//        result.append("\n-----END PUBLIC KEY-----");
//        return result.toString();
//    }
//}
//
//
//
//
//
//
////    private final UserService userService;
////    private final PasswordEncoder passwordEncoder;
////    private final AccessTokenManager accessTokenManager;
////
////    @Override
////    public void run(String... args) throws Exception {
////
////        User user = User.builder().email("sadiqqenberov26@gmail.com").password("password").build();
////        user.setId(1);
////
////        final String token = accessTokenManager.generate(user);
////
////        System.out.println(token);
////
////        System.out.println(accessTokenManager.read(token).get("email", String.class));
////    }}
//
//
//
//
