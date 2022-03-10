package fr.sanchez.testSpringBoot.Repository;

import fr.sanchez.testSpringBoot.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
