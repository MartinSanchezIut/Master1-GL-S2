package fr.sanchez.testSpringBoot.Controller;

import fr.sanchez.testSpringBoot.Models.Location;
import fr.sanchez.testSpringBoot.Models.User;
import fr.sanchez.testSpringBoot.Repository.LocationRepository;
import fr.sanchez.testSpringBoot.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loc")
public class LocationController {

    @Autowired
    private LocationRepository location;



    @GetMapping
    public List<Location> list() {
        return location.findAll();
    }
    @GetMapping
    @RequestMapping("{id}")
    public Location get(@PathVariable Long id) {
        return location.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Location create(@RequestBody final Location loc) {
        return location.saveAndFlush(loc);
    }


}
