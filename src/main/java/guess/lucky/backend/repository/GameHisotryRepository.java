package guess.lucky.backend.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import guess.lucky.backend.models.Game;

public interface GameHisotryRepository extends JpaRepository<Game,Long> {
    List<Game> findByUserIdAndGamePlayedAt(Long user_id,Date gamePlayedAt); 
    
}
