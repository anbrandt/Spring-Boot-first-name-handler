package pl.sda.user;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class UserController {

    private AtomicInteger idGenerator;
    private List<User> users;

    public UserController() {
        idGenerator = new AtomicInteger(0);
        users = new ArrayList<>();
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public int create(@RequestBody User user) {
        int id = idGenerator.getAndIncrement();
        user.setId(id);
        users.add(user);
        return id;
    }


    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User get(@PathVariable("id") int id) {
        Optional<User> first = users.stream().filter(user -> user.getId() == id).findFirst();
        return first.orElse(null);
    }


    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public User update(@RequestBody User user) {
        Optional<User> first = users.stream().filter(usr -> usr.getId() == user.getId()).findFirst();
        first.ifPresent(usr -> usr.setName(user.getName()));
        return first.orElse(null);
    }


    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public User delete(@PathVariable("id") int id) {
        Optional<User> first = users.stream().filter(user -> user.getId() == id).findFirst();
        first.ifPresent(usr -> users.remove(usr));
        return first.orElse(null);
    }

}
