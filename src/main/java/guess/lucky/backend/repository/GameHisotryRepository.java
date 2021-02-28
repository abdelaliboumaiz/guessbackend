package guess.lucky.backend.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import guess.lucky.backend.models.Game;
import org.springframework.data.jpa.repository.Query;

public interface GameHisotryRepository extends JpaRepository<Game,Long> {
    List<Game> findByUserIdAndGamePlayedAt(Long user_id,Date gamePlayedAt);

    @Query("select distinct g.gamePlayedAt from Game g where g.user.id = ?1")
    List<Date> getPlayedDate(Long id);

    List<Game> findGameByGamePlayedAtAndUser_Id(Date date, Long id);

    @Query("select count(g) from Game g where g.gamePlayedAt = ?1")
    int gameCountByDay();

    List<Game> findGameByGamePlayedAt(Date date);

    
}
