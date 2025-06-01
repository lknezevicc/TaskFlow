package hr.lknezevic.taskflow.taskflowserver.repository;

import hr.lknezevic.taskflow.taskflowserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
