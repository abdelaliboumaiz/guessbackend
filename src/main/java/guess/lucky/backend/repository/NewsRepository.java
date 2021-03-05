package guess.lucky.backend.repository;

import guess.lucky.backend.models.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {

    @Query("select n from News n where n.expiredDate >= ?1")
    List<News> getNotExpiredNews(Date date);
}
