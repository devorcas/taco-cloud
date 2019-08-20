package tacos.web.api;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import tacos.Taco;
import tacos.data.TacoRepository;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RepositoryRestController
public class RecentTacoController {
    private TacoRepository repo;

    public RecentTacoController(TacoRepository repo) {
        this.repo = repo;
    }

    @GetMapping(path = "/tacos/recent", produces = "application/json")
    public ResponseEntity<Resources<TacoResource>> recentTacos(){
        PageRequest page = PageRequest.of(
                0, 12, Sort.by("createdAt").descending());
        List<Taco> tacos = repo.findAll(page).getContent();

        List<TacoResource> tacoResources =
                new TacoResourceAssembler().toResources(tacos);
        Resources<TacoResource> recentTacos =
                new Resources<TacoResource>(tacoResources);
        recentTacos.add(
                linkTo(methodOn(DesignTacoController.class).recentTacos())
                        .withRel("recents"));
        return new ResponseEntity<>(recentTacos, HttpStatus.OK);
    }
}
