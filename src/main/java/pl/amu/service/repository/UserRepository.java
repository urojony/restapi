package pl.amu.service.repository;

import org.springframework.stereotype.Component;
import pl.amu.service.rest.UsersService;
import pl.amu.service.rest.model.User;

import javax.ws.rs.core.EntityTag;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserRepository implements UsersService {

    private static final List<User> users = new ArrayList<>();
    private int version=1;
    public UserRepository() {
        save(new User("Mieszko Pierwszy", "mieszko", "mieszko1@o2.pl"));//, Arrays.asList("król") ));
        save(new User("Bolesław Chrobry", "bolek", "bolek@wp.pl"));//, Arrays.asList("król") ));
        save(new User("Jancio Wodnik", "janek", "janw@gmail.com" )); //, Arrays.asList("postać")
    }

    public List<User> getUsers(){
        return users;
    }
    
    public User save(User user){
        String login=user.getLogin();
        User dbUser = findByLogin(login);
        user.setLogin(login);
        if(dbUser == null) {
//            dbUser = user;

            users.add(user);

        }
/*
        dbUser.setName(user.getName());
        dbUser.setEmail(user.getEmail());*/

        return user;
    }


    public User findByLogin(String login) {
        for(User user : users){
            if (login.equalsIgnoreCase(user.getLogin())){
                return user;
            }
        }
        return null;
    }
    
    public User remove(String login){
        User user = findByLogin(login);
        if (user != null){
            users.remove(user);
        }
        return user;
    }
    public void nextversion(){
        version+=1;
    }
    public int showversion(){
        return version;
    }
}
