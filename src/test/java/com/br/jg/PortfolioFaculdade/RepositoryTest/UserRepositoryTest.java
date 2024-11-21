package com.br.jg.PortfolioFaculdade.RepositoryTest;

import com.br.jg.PortfolioFaculdade.Entities.User;
import com.br.jg.PortfolioFaculdade.Repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;



@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Test
    @DisplayName("Should return all users added on the DB")
    void findUsers(){
        saveListUsers();
        List<User> users = userRepository.findAll();
        assertThat(users.isEmpty()).isFalse();
    }
    private void saveListUsers(){
        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
        List<User> users = new ArrayList<>();
        users.add(u1);
        users.add(u2);
        this.userRepository.saveAll(users);
    }
}
