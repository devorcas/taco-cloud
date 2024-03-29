package tacos.web.api;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import tacos.Taco;

public class TacoResourceAssembler
        extends ResourceAssemblerSupport<Taco, TacoResource> {

    public TacoResourceAssembler() {
        super(DesignTacoController.class, TacoResource.class);
    }

    @Override
    public TacoResource toResource(Taco taco) {
        return createResourceWithId(taco.getId(), taco);
    }

    @Override
    protected TacoResource instantiateResource(Taco entity) {
        return new TacoResource(entity);
    }
}
