package tacos.authentication.users;

import org.springframework.data.repository.CrudRepository;

public interface AccountUserRepository extends CrudRepository<AccountUser, Long> {
    AccountUser findByUsername(String username);
}