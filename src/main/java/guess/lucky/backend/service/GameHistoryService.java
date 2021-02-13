package guess.lucky.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guess.lucky.backend.models.config.User;
import guess.lucky.backend.repository.GameHisotryRepository;

@Service
public class GameHistoryService {
    
    @Autowired
    private GameHisotryRepository gameHisotry; 
    
    public boolean isPermissibleToPlay(User u) {
        // List<Game> playedGameInTheCurrentDate = gameHisotry.findby
        
        return false; 
    }

}
