package guess.lucky.backend.service;

import java.text.ParseException;
import java.util.*;

import guess.lucky.backend.exceptions.ServiceException;
import guess.lucky.backend.repository.GameHisotryRepository;
import io.swagger.models.auth.In;
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

    @Autowired
    private GameHisotryRepository gameHisotryRepository;
    
    public User playGame(GameRequest game){
        Optional<User> user = userRepository.findById(game.getUser_id());
        if(user.get()==null) {
            throw new ServiceException("Utilisateur non trouver");
        }
        boolean isPermissibleToPlay = gameHistoryService.isPermissibleToPlay(user.get());
        if(!isPermissibleToPlay) {
            throw new ServiceException("Vous avez plus d'essais");
        }
        Game currentGame = new Game();
        String tmpNumbers[] = game.getChosenNumbers().split(",");
        int numbers[] = new int[tmpNumbers.length];
        for(int i = 0; i<tmpNumbers.length; i++){
            numbers[i] = Integer.parseInt(tmpNumbers[i]);
        }
        numbers = Arrays.stream(numbers).sorted().toArray();

        String tmpHoroscopes[] = game.getChosenHoroscopes().split(",");
        int horoscopes[] = new int[tmpHoroscopes.length];
        for(int i = 0; i<tmpHoroscopes.length; i++){
            horoscopes[i] = Integer.parseInt(tmpHoroscopes[i]);
        }
        horoscopes = Arrays.stream(horoscopes).sorted().toArray();

        String strNumbers[] = Arrays.stream(numbers)
                .mapToObj(String::valueOf)
                .toArray(String[]::new);

        String strHoroscopes[] = Arrays.stream(horoscopes)
                .mapToObj(String::valueOf)
                .toArray(String[]::new);

        currentGame.setChosenNumbers(String.join(",", strNumbers));
        currentGame.setHoroscopes(String.join(",", strHoroscopes));
        currentGame.setUser(user.get());
        currentGame.setGamePlayedAt(new Date());
        gameHistoryService.saveGameToHistory(currentGame);
        return user.get();
    }
    
    public HashMap<String, List<Game>> participation(Long id){
        HashMap<String, List<Game>> stringGameHashMap = new HashMap<>();
        gameHisotryRepository.getPlayedDate(id).forEach( item ->
                stringGameHashMap.put(item.toString(), gameHisotryRepository.findGameByGamePlayedAtAndUser_Id(item, id))
        );
        return stringGameHashMap;
    }

}
