package fr.sanchez.testSpringBoot.Controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.sanchez.testSpringBoot.Models.Location;
import fr.sanchez.testSpringBoot.Repository.LocationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loc")
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;



    @GetMapping
    public List<Location> list() {
        return locationRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Location get(@PathVariable Long id) {
        if(locationRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location with ID "+id+" not found");
        }
        return locationRepository.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Location create(@RequestBody final Location loc) {
        return locationRepository.saveAndFlush(loc);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        if(locationRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location with ID "+id+" not found");
        }

        locationRepository.deleteById(id);
    }

    @RequestMapping(value="{id}",method = RequestMethod.PUT)
    public Location update(@PathVariable Long id, @RequestBody Location loc) {
        if(locationRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location with ID "+id+" not found");
        }

        Location existingLoc = locationRepository.getById(id);
        BeanUtils.copyProperties(loc, existingLoc,"location_id");
        return locationRepository.saveAndFlush(loc);
    }

}
