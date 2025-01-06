package tacos.web.api;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tacos.Ingredient;
import tacos.data.IngredientRepository;

@RestController
@RequestMapping(path="/api/ingredients", produces = "application/json")
@CrossOrigin(origins = "http://204.0.137.204:9090")
public class APIIngredientController {
    private IngredientRepository ingredientRepo;

    public APIIngredientController(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @GetMapping(params = "all")
    public Iterable<Ingredient> allIngredients() {
        
        return ingredientRepo.findAll();
    }

    @PostMapping(consumes = "application/json") 
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredient postIngredient(@RequestBody Ingredient ingredient) {
        return ingredientRepo.save(ingredient);
    }

}
