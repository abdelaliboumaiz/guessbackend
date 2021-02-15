package guess.lucky.backend.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guess.lucky.backend.models.Game;
import guess.lucky.backend.models.config.User;
import guess.lucky.backend.repository.GameHisotryRepository;

@Service
public class GameHistoryService {
    
    private static final int NUMBEROFPERMISSIBLETRIESPERDAY = 5; 
    private static final int NOSPONSORSHIPAVAILABLE = 0 ; 
    
    @Autowired
    private GameHisotryRepository gameHisotry; 
    @Autowired 
    private SponsorshipConsumptionService sponsorshipConsumptionService; 
    
    public boolean isPermissibleToPlay(User u) {
        List<Game> playedGameInTheCurrentDate = gameHisotry.findByUserIdAndGamePlayedAt(u.getId(), new Date()); 
        int numberOfPlayedTodaySessions = playedGameInTheCurrentDate.size(); 
        if(numberOfPlayedTodaySessions < NUMBEROFPERMISSIBLETRIESPERDAY) {
            return true; 
        }
        else { 
            int numberOfPeopleISponsor = sponsorshipConsumptionService.getHowManyPeopleUserSponsor(u); 
            if(numberOfPeopleISponsor > NOSPONSORSHIPAVAILABLE) {
                int usedTodayAdditionalTries = sponsorshipConsumptionService.getUsedTodayAdditionalTries(u);
                if(usedTodayAdditionalTries > numberOfPeopleISponsor * 5) {
                    return false; 
                }
                else { 
                    sponsorshipConsumptionService.incrementDailySponsorshipConsumption(u);
                    return true; 
                }
                
            }
        }
        return false; 
    }
    
    public Game saveGameToHistory(Game g) {
        return gameHisotry.save(g); 
    }

}
