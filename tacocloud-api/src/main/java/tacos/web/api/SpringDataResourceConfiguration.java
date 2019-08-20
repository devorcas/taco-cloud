package tacos.web.api;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.ResourceProcessor;
import tacos.Taco;

@Configuration
public class SpringDataResourceConfiguration {

    @Bean
    public ResourceProcessor<PagedResources<Taco>> tacoProcessor(EntityLinks entityLinks){
        return new ResourceProcessor<PagedResources<Taco>>() {
            @Override
            public PagedResources<Taco> process(PagedResources<Taco> resources) {
                resources.add(
                        entityLinks.linkFor(Taco.class)
                        .slash("recent")
                        .withRel("resents")
                );
                return resources;
            }
        };
    }
}
