package fr.sanchez.testSpringBoot.Repository;

import fr.sanchez.testSpringBoot.Models.Authorities;
import fr.sanchez.testSpringBoot.Models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepository  extends JpaRepository<Authorities, Long> {
}
