package tacos.authentication;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import tacos.authentication.users.AccountUser;
import tacos.authentication.users.AccountUserRepository;
@SpringBootApplication
public class AuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }

    // @Bean
    // public ApplicationRunner dataLoader(
    //     AccountUserRepository repo, PasswordEncoder encoder) {
    //         // return args -> {
    //         //     repo.save(
    //         //         new AccountUser("habuma", encoder.encode("password"), "habuma", "ROLE_ADMIN" )
    //         //     );
    //         //     repo.save(
    //         //         new AccountUser("tacochef", encoder.encode("password"), "tacochef", "ROLE_ADMIN")
    //         //     );
    //         // };
    //         return args -> {
    //             repo.save(
    //                 new AccountUser("habuma", encoder.encode("password"),"ROLE_ADMIN" )
    //             );
    //             repo.save(
    //                 new AccountUser("tacochef", encoder.encode("password"),"ROLE_ADMIN")
    //             );
    //         };
    //     }
}
