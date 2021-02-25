package guess.lucky.backend.repository;

import guess.lucky.backend.models.WinGames;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface WinGameRepository extends JpaRepository<WinGames,Long> {

    Optional<WinGames> findByWinDate(Date date);
}
