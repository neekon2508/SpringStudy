package tacos.data;

import org.springframework.data.repository.CrudRepository;

import tacos.AccountUser;


public interface AccountUserRepository extends CrudRepository<AccountUser, Long>{
    AccountUser findByUsername(String username);
}
