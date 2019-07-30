package tacos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import tacos.Ingredient;
import tacos.Order;
import tacos.Taco;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;
    private TacoRepository tacoRepository;

    @ModelAttribute(name = "order")
    private Order order(){
        return new Order();
    }

    @ModelAttribute(name = "taco")
    private Taco taco(){
        return new Taco();
    }

    @Autowired
    public DesignTacoController(
            IngredientRepository ingredientRepository,
            TacoRepository tacoRepository) {
        this.ingredientRepo = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(ingredients::add);

        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }

        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco design,
                                Errors errors,
                                @ModelAttribute Order order) {
        if (errors.hasErrors()) {
            return "design";
        }

        Taco saved = tacoRepository.save(design);
        order.addDesign(saved);

        log.info("Processing design: " + design);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(
            List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getKind().equals(type))
                .collect(Collectors.toList());
    }
}
