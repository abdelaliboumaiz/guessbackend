package guess.lucky.backend.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guess.lucky.backend.models.Sponsorship;
import guess.lucky.backend.models.SponsorshipDailyConsumption;
import guess.lucky.backend.models.config.User;
import guess.lucky.backend.repository.SponsorshipConsumptionRepository;
import guess.lucky.backend.repository.SponsorshipRepository;

@Service
public class SponsorshipConsumptionService {
    
    @Autowired
    private SponsorshipRepository sponsorShipRepository; 
    @Autowired 
    private SponsorshipConsumptionRepository consumptionRepository; 
    
    public void incrementDailySponsorshipConsumption(User u) {
        SponsorshipDailyConsumption newlyCreatedConsumption = new SponsorshipDailyConsumption(); 
        SponsorshipDailyConsumption oldConsumption = consumptionRepository.findByConcernedUserAndAndRequestDate(u.getId(), new Date());
        newlyCreatedConsumption.setId(oldConsumption.getId());
        newlyCreatedConsumption.setConcernedUser(oldConsumption.getConcernedUser());
        Integer newConsumption = oldConsumption.getUsedTries()+1; 
        newlyCreatedConsumption.setUsedTries(newConsumption);
    }
    
    public int getHowManyPeopleUserSponsor(User u) {
        Sponsorship s = sponsorShipRepository.findBySponsor_Id(u.getId());
        return s.getHowManyPeopleISponsor(); 
    }

    public int getUsedTodayAdditionalTries(User u) {
        SponsorshipDailyConsumption dailyConsumption = consumptionRepository.findByConcernedUserAndAndRequestDate(u.getId(), new Date());
        return dailyConsumption.getUsedTries(); 
    }
    

}
