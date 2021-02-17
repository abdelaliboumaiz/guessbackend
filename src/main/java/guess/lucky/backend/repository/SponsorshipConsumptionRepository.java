package guess.lucky.backend.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import guess.lucky.backend.models.SponsorshipDailyConsumption;

@Repository
public interface SponsorshipConsumptionRepository extends JpaRepository<SponsorshipDailyConsumption,Long> {
    SponsorshipDailyConsumption findByConcernedUserAndAndRequestDate(Long user_id,Date requestDate);
}
