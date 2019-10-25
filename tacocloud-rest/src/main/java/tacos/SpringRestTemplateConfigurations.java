package tacos;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Configuration
public class SpringRestTemplateConfigurations {

    @Bean
    public Traverson traverson() {
        return new Traverson(URI.create("http://localhost:8080/api"),
                MediaTypes.HAL_JSON);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
