package guess.lucky.backend.service;

import java.util.Date;
import java.util.List;

import guess.lucky.backend.repository.config.UserRepository;
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
    private UserRepository userRepository;

    
    public boolean isPermissibleToPlay(User u) {
        List<Game> playedGameInTheCurrentDate = gameHisotry.findByUserIdAndGamePlayedAt(u.getId(), new Date()); 
        int numberOfPlayedTodaySessions = playedGameInTheCurrentDate.size(); 
        if(numberOfPlayedTodaySessions < NUMBEROFPERMISSIBLETRIESPERDAY) {
            decrementalDailyGame(u);
            return true; 
        }
        else { 
            if(u.getHeartWin() > NOSPONSORSHIPAVAILABLE){
                decrementalWinGame(u);
                return true;
            }
        }
        return false; 
    }
    
    public Game saveGameToHistory(Game g) {
        return gameHisotry.save(g); 
    }

    public void decrementalDailyGame(User u){
        u.setHeartPeerDay(u.getHeartPeerDay() - 1);
        userRepository.save(u);
    }

    public void decrementalWinGame(User u){
        u.setHeartWin(u.getHeartWin() - 1);
        userRepository.save(u);
    }
}
