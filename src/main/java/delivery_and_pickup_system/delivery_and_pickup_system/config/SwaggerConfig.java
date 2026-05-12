package delivery_and_pickup_system.delivery_and_pickup_system.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "Authorization",
        scheme = "bearer",
        bearerFormat = "JWT",
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.HEADER
)
public class SwaggerConfig {

    private static final String SECURITY_SCHEME_NAME = "Authorization";

    // ===================== OPEN API CONFIG =====================
    @Bean
    public OpenAPI openAPI() {

        Contact contact = buildContact();
        Info info = buildApiInfo(contact);

        return new OpenAPI()
                .info(info)
                .addSecurityItem(
                        new SecurityRequirement().addList(SECURITY_SCHEME_NAME)
                );
    }

    // ===================== CONTACT =====================
    private Contact buildContact() {
        Contact contact = new Contact();
        contact.setEmail("sadiqqenberov@gmail.com");
        contact.setName("SadiqQenberov");
        contact.setUrl("https://www.sadiqqenberov.com");
        return contact;
    }

    // ===================== API INFO =====================
    private Info buildApiInfo(Contact contact) {
        return new Info()
                .title("Delivery and Pickup API")
                .version("1.0")
                .contact(contact)
                .description("This API is used for cargo post services.")
                .termsOfService("https://www.sadiqqenberov.com/terms");
    }
}