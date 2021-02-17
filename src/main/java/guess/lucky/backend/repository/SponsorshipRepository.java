package guess.lucky.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import guess.lucky.backend.models.Sponsorship;

@Repository
public interface SponsorshipRepository extends JpaRepository<Sponsorship,Long> {
    Sponsorship findByUserId(Long user_id); 
}
