package guess.lucky.backend.repository;

import guess.lucky.backend.models.Winners;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WinnersRepository extends JpaRepository<Winners, Long> {

    List<Winners> findByisCheckAndUser_Id(boolean check, long id);
}
