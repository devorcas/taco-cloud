package tacos.kitchen.messaging.jms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import tacos.Order;

import java.util.HashMap;
import java.util.Map;

@Profile({"jms-template", "jms-listener"})
@Configuration
public class MessagingConfig {

    @Bean
    public MappingJackson2MessageConverter messageConverter(){
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();

        converter.setTypeIdPropertyName("_typeId");
        Map<String, Class<?>> typeIdMapping = new HashMap<>();
        typeIdMapping.put("order", Order.class);
        converter.setTypeIdMappings(typeIdMapping);
        return converter;
    }
}
