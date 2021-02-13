package guess.lucky.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guess.lucky.backend.exceptions.ResourceNotFound;
import guess.lucky.backend.models.Game;
import guess.lucky.backend.models.config.User;
import guess.lucky.backend.repository.config.UserRepository;
import guess.lucky.backend.request.GameRequest;

// i need to get today playing history 
// i can check  
@Service
public class GameService {
    
    @Autowired
    private UserRepository userRepository; 
    @Autowired 
    private GameHistoryService gameHistoryService; 
    
    public void playGame(GameRequest game) {
        
        Optional<User> user = userRepository.findById(game.getUser_id());
        if(user.get()==null) {
            throw new ResourceNotFound(); 
        }
        boolean isPermissibleToPlay = gameHistoryService.isPermissibleToPlay(user.get()); 
        Game currentGame = new Game(); 
        
    }
    
  

}
