package tacos.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import tacos.AccountUser;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.TacoOrder;
import tacos.Taco;
import tacos.data.AccountUserRepository;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

  private final IngredientRepository ingredientRepo;

  private TacoRepository tacoRepo;

  private AccountUserRepository accountUserRepo;

  @Autowired
  public DesignTacoController(
        IngredientRepository ingredientRepo,
        TacoRepository tacoRepo, AccountUserRepository userRepository) {
    this.ingredientRepo = ingredientRepo;
    this.tacoRepo = tacoRepo;
    this.accountUserRepo = userRepository;
  }

  @ModelAttribute
  public void addIngredientsToModel(Model model) {
    List<Ingredient> ingredients = new ArrayList<>();
    ingredientRepo.findAll().forEach(i -> ingredients.add(i));

    Type[] types = Ingredient.Type.values();
    for (Type type : types) {
      model.addAttribute(type.toString().toLowerCase(),
          filterByType(ingredients, type));
    }
  }

  @ModelAttribute(name = "tacoOrder")
  public TacoOrder order() {
    return new TacoOrder();
  }

  @ModelAttribute(name = "taco")
  public Taco taco() {
    return new Taco();
  }
  @ModelAttribute(name="user")
  public AccountUser user(Principal principal) {
    String username = principal.getName();
    AccountUser user = accountUserRepo.findByUsername(username);
    return user;
  }
  @GetMapping
  public String showDesignForm(@ModelAttribute TacoOrder tacoOrder) {
    log.info("order: {}", tacoOrder);
    return "design";
  }

  @PostMapping
  public String processTaco(
      @Valid Taco taco, Errors errors,
      @ModelAttribute TacoOrder tacoOrder) {

    if (errors.hasErrors()) {
      return "design";
    }
    /*Không lưu taco vào database tại thời điểm này. Trong TacoOrder ta sử dụng @CascadeType.ALL có tác dụng tự động save Taco khi save TacoOrder. Nếu lưu Taco 
    sẽ bị lỗi detached persist
    //Taco saved = tacoRepo.save(taco);log.info("Save Taco in database: {}", saved); 
    */
    tacoOrder.addTaco(taco);log.info("Add taco in order: {}", tacoOrder);

    return "redirect:/orders/current";
  }

  private Iterable<Ingredient> filterByType(
      List<Ingredient> ingredients, Type type) {
    return ingredients
              .stream()
              .filter(x -> x.getType().equals(type))
              .collect(Collectors.toList());
  }

}
