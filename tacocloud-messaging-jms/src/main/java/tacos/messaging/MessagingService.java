package tacos.messaging;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import tacos.Order;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MessagingService {

    @Bean
    public MappingJackson2MessageConverter messageConverter() {
        MappingJackson2MessageConverter messageConverter =
                new MappingJackson2MessageConverter();

        messageConverter.setTypeIdPropertyName("_typeId");

        Map<String, Class<?>> typeIdMapping = new HashMap<>();
        typeIdMapping.put("order", Order.class);
        messageConverter.setTypeIdMappings(typeIdMapping);

        return messageConverter;
    }
}
