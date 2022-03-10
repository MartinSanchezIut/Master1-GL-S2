package fr.sanchez.testSpringBoot.Controller;

import fr.sanchez.testSpringBoot.Models.User;
import fr.sanchez.testSpringBoot.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {


    @Autowired
    private UserRepository user;


    @GetMapping
    public List<User> list() {
        return user.findAll();
    }
    @GetMapping
    @RequestMapping("{id}")
    public User get(@PathVariable Long id) {
        return user.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody final User usr) {
        return user.saveAndFlush(usr);
    }

}
