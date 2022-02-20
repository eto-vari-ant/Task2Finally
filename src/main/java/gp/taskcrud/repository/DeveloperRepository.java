package gp.taskcrud.repository;

import gp.taskcrud.model.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {
    Optional<Developer> findDeveloperById(Long id);
    boolean existsById(Long id);
}
