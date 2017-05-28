package pl.amu.service.rest;

//import org.springframework.security.access.annotation.Secured;
import pl.amu.service.rest.model.User;

import java.util.List;
public interface UsersService {
    List<User> getUsers();
    User findByLogin(String login);
    User remove(String login);
    User save(User user);
    void nextversion();
    int showversion();
}
