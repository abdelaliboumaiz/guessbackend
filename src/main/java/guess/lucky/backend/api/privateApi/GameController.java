package guess.lucky.backend.api.privateApi;

import guess.lucky.backend.models.Game;
import guess.lucky.backend.models.WinGames;
import guess.lucky.backend.models.config.User;
import guess.lucky.backend.service.UserService;
import guess.lucky.backend.service.WinGameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import guess.lucky.backend.request.GameRequest;
import guess.lucky.backend.service.GameService;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/game")
public class GameController {
    private static final Logger log = LoggerFactory.getLogger(GameController.class);
    @Autowired
    private GameService gameService;

    @Autowired
    private WinGameService winGameService;
    
    @PostMapping("/play")
    public ResponseEntity<User> playGame(@RequestBody GameRequest game){
        log.info("Game created ...");
        return new ResponseEntity<>(gameService.playGame(game),HttpStatus.OK);
    }

    @GetMapping("/participation")
    public ResponseEntity<HashMap<String, List<Game>>> participation(@RequestParam Long id){
        return new ResponseEntity<>(gameService.participation(id), HttpStatus.OK);
    }

    @GetMapping("/winnerGame")
    public ResponseEntity<WinGames> winGameDayBack(){
        return new ResponseEntity<>(winGameService.winGameDayBack(), HttpStatus.OK);
    }
}
