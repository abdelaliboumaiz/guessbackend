package guess.lucky.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import guess.lucky.backend.models.Game;

public interface GameHisotryRepository extends JpaRepository<Game,Long> {
    // List<Game> findByUserIdAndGamePlayedAt(Date gamePlayedAt); 
}
