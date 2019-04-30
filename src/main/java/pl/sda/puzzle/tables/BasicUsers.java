package pl.sda.puzzle.tables;

import org.springframework.stereotype.Component;
import pl.sda.puzzle.UserRepository;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component
public class BasicUsers {
    private UserRepository userRepository;

    public BasicUsers(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init(){
        List<User> users = Arrays.asList(
                new User("user@user.pl", "user", User.Role.USER),
                new User("admin@admin.pl", "admin", User.Role.ADMIN)
        );

        users.forEach(userRepository::save);
    }
}
