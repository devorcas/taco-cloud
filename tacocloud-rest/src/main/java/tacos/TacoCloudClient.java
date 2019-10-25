package tacos;

import org.springframework.hateoas.client.Traverson;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class TacoCloudClient {

    private RestTemplate restTemplate;
    private Traverson traverson;



//    public Ingredient getIngredientById(String ingredientId){
//        return restTemplate.getForObject("http://localhost:8080/ingredients/{id}",
//                Ingredient.class, ingredientId);
//    }

//    public Ingredient getIngredientById(String ingredientId){
//        return restTemplate.getForObject("http://localhost:8080/ingredients/{id}",
//                Ingredient.class, Collections.singletonMap("id", ingredientId));
//    }

//    public Ingredient getIngredientById(String ingredientId){
//        URI uri = UriComponentsBuilder
//                .fromHttpUrl("http://localhost:8080/ingredients/{id}")
//                .build(Collections.singletonMap("id", ingredientId));
//        return restTemplate.getForObject(uri, Ingredient.class);
//    }

    public Ingredient getIngredientById(String ingredientId) {
        ResponseEntity<Ingredient> responseEntity =
                restTemplate.getForEntity("http://localhost:8080/ingredients/{id}",
                        Ingredient.class, ingredientId);
        return responseEntity.getBody();
    }

    public void updateIngredient(Ingredient ingredient) {
        restTemplate.put("http://localhost:8080/ingredients/{id}"
                ,ingredient, ingredient.getId());
    }

    public void deleteIngredient(Ingredient ingredient) {
        restTemplate.delete("http://localhost:8080/ingredients/{id}"
                ,ingredient, ingredient.getId());
    }

//    public Ingredient createIngredient(Ingredient ingredient) {
//        return restTemplate.postForObject("http://localhost:8080/ingredients/{id}"
//                ,ingredient, Ingredient.class);
//    }

    public URI createIngredient(Ingredient ingredient) {
        return restTemplate.postForLocation("http://localhost:8080/ingredients/{id}"
                ,ingredient, Ingredient.class);
    }

}
