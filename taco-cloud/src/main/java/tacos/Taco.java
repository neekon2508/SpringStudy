package tacos;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
@Entity
public class Taco {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "taco_order")
    private TacoOrder order;

  @NotNull
  @Size(min=5, message="Name must be at least 5 characters long")
  private String name;

  private Date createdAt;
  @PrePersist
  void createdAt() {
    this.createdAt = new Date();
  }

  @Size(min=1, message="You must choose at least 1 ingredient")
  @ManyToMany()
  @JoinTable(name = "taco_ingredient", joinColumns = @JoinColumn(name = "taco_id"), inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
  private List<Ingredient> ingredients;

}
