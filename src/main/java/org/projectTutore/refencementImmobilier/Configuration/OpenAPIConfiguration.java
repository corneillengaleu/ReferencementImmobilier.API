package org.projectTutore.refencementImmobilier.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

import java.util.List;

@Configuration
public class OpenAPIConfiguration {
    @Value("${server.url}")
    String url;
    @Bean
    public OpenAPI defineOpenApi() {

        Server server = new Server();
        server.setUrl(url);
        server.setDescription("Development");

        // Configuration du contact
        Contact myContact = new Contact();
        myContact.setName("Corneille NGALEU");
        myContact.setEmail("corneillngaleu@gmail.com");

        // Informations sur l'API
        Info information = new Info()
                .title("REFERENCEMENT IMMOBILIER")
                .version("1.0")
                .description("This API is designed for real estate referencing.")
                .contact(myContact);

        // Retourne la configuration OpenAPI
        return new OpenAPI()
                .info(information)
                .servers(List.of(server));
    }
}