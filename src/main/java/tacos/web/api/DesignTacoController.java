package tacos.web.api;

import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tacos.Taco;
import tacos.data.TacoRepository;

import java.awt.print.Pageable;
import java.util.Optional;

@RestController
@RequestMapping(path = "/design",
produces = "application/json")
@CrossOrigin(origins = "*")
public class DesignTacoController {
    private TacoRepository tacoRepo;

    public DesignTacoController(TacoRepository tacoRepo) {
        this.tacoRepo = tacoRepo;
    }

    @GetMapping(path = "/recent")
    public Iterable<Taco> recentTacos(){
        PageRequest page = PageRequest.of(
                0,12, Sort.by("createdAt").descending()
        );
        return tacoRepo.findAll(page).getContent();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Taco> gerById(@PathVariable Long id){
        Optional<Taco> optTaco = tacoRepo.findById(id);
        if (optTaco.isPresent()){
            return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}