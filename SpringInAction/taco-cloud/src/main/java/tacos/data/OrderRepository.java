package tacos.data;

import java.util.List;

import org.springframework.data.domain.*;
import org.springframework.data.repository.CrudRepository;

import tacos.AccountUser;
import tacos.TacoOrder;

public interface OrderRepository 
         extends CrudRepository<TacoOrder, Long> {
    List<TacoOrder> findByUserOrderByPlacedAtDesc(AccountUser user, Pageable pageable);
}
