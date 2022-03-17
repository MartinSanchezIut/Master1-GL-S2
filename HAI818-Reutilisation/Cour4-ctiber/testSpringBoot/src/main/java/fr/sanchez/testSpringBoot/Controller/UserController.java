package fr.sanchez.testSpringBoot.Controller;

import fr.sanchez.testSpringBoot.Models.Location;
import fr.sanchez.testSpringBoot.Models.User;
import fr.sanchez.testSpringBoot.Repository.LocationRepository;
import fr.sanchez.testSpringBoot.Repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping
    public List<User> list() {
        return userRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public User get(@PathVariable Long id) {
        if(userRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID "+id+" not found");
        }

        return userRepository.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody final User usr) {
        return userRepository.saveAndFlush(usr);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        if(userRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID "+id+" not found");
        }

        User u = userRepository.getById(id) ;
        for(Location l : u.getLocations()) {
            locationRepository.deleteById(l.getLocation_id());
        }
        userRepository.deleteById(id);
    }

    @RequestMapping(value="{id}",method = RequestMethod.PUT)
    public User update(@PathVariable Long id, @RequestBody User user) {
        if(userRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID "+id+" not found");
        }

        User existingUser = userRepository.getById(id);
        BeanUtils.copyProperties(user, existingUser,"user_id");
        return userRepository.saveAndFlush(existingUser);
    }
}
