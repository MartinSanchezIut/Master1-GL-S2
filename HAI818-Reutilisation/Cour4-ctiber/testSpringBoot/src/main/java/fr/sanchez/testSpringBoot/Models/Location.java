package fr.sanchez.testSpringBoot.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.List;

@Entity(name="locations")
@Access(AccessType.FIELD)
public class Location {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long location_id ;
    @Column
    private Long latitude ;
    @Column
    private Long longitude ;
    @Column
    private Timestamp location_date ;

    @ManyToMany(mappedBy = "locations")
    @JsonIgnore
    private List<User> users;


    public Location() {
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Long getLocation_id() {
        return location_id;
    }
    public void setLocation_id(Long location_id) {
        this.location_id = location_id;
    }
    public Long getLatitude() {
        return latitude;
    }
    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }
    public Long getLongitude() {
        return longitude;
    }
    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }
    public Timestamp getLocation_date() {
        return location_date;
    }
    public void setLocation_date(Timestamp location_date) {
        this.location_date = location_date;
    }
}
