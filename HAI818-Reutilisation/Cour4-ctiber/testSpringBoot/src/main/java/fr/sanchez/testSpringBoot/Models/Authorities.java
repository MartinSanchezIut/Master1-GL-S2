package fr.sanchez.testSpringBoot.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Entity(name="authorities")
@Access(AccessType.FIELD)
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"} )
public class Authorities {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long authority_id ;
    // authority_id serial primary key,

    @Column
    @ManyToOne(optional = false, targetEntity = User.class)
    String username ;
    // username varchar(50) NOT NULL REFERENCES users (username),

    @Column
    String authority;
    // authority varchar(50) NOT NULL DEFAULT 'ROLE_USER'


    public Authorities() {
    }

    public Long getAuthority_id() {
        return authority_id;
    }

    public void setAuthority_id(Long authority_id) {
        this.authority_id = authority_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
