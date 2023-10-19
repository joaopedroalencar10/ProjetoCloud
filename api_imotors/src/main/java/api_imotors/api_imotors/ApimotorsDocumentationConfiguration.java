package api_imotors.api_imotors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
    


@Configuration
public class ApimotorsDocumentationConfiguration {
     @Bean
     public OpenAPI apiDocConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("Apresentação ap1")
                        .description("Apresentação ap1 de projeto cloud")
                        .version("1.0")
                        .contact(new Contact()
                                .name("João Pedro,Caio,Lucca,Ana,Leonardo")
                                .email("")));
    }
}
