package tacos.web;

import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.*;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import tacos.AccountUser;
import tacos.Taco;
import tacos.TacoOrder;
import tacos.data.OrderRepository;
import tacos.data.TacoRepository;
@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
@ConfigurationProperties(prefix = "taco.orders")
public class OrderController {

  private OrderRepository orderRepo;
  
  private OrderProps props;

  public OrderController(OrderRepository orderRepo, OrderProps props) {
    this.orderRepo = orderRepo;
    this.props = props;
  }

  @GetMapping("/current")
  public String orderForm(@AuthenticationPrincipal AccountUser user, @ModelAttribute TacoOrder tacoOrder) {
    log.info("order: "+tacoOrder);
    if(user == null) log.info("User null!");
    log.info("user: "+user.getFullname());
    log.info("tacos: "+tacoOrder.getTacos());
    if (tacoOrder.getDeliveryName() == null)
      tacoOrder.setDeliveryName(user.getFullname());
    if(tacoOrder.getDeliveryStreet() == null)
      tacoOrder.setDeliveryStreet(user.getStreet());
    if(tacoOrder.getDeliveryCity()==null)
      tacoOrder.setDeliveryCity(user.getCity());
    if(tacoOrder.getDeliveryState()==null)
      tacoOrder.setDeliveryState(user.getState());
    if(tacoOrder.getDeliveryZip()==null)
      tacoOrder.setDeliveryZip(user.getZip());
   
    return "orderForm";
  }

  @PostMapping
  public String processOrder(@Valid @ModelAttribute TacoOrder tacoOrder, Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal AccountUser user) {
    log.info("Post tacoOrder: "+tacoOrder.getId()+" tacos: "+tacoOrder.getTacos());
    if (errors.hasErrors()) {
      return "orderForm";
    }
    tacoOrder.setUser(user);
    TacoOrder saved = orderRepo.save(tacoOrder);
    log.info("Save Taco Order: "+saved.getId()+" tacos: "+saved.getTacos());
    sessionStatus.setComplete();

    return "redirect:/";
  }

  @GetMapping
  public String ordersForUser(
    @AuthenticationPrincipal AccountUser user, Model model) {
      
     Pageable pageable = PageRequest.of(0, props.getPageSize());
     model.addAttribute(
      "orders", orderRepo.findByUserOrderByPlacedAtDesc(user, pageable));
     return "orderList";
    }
}
