package guess.lucky.backend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import guess.lucky.backend.request.GameRequest;
import guess.lucky.backend.service.GameService;

@RestController("/api")
public class GameController {
    
    @Autowired
    private GameService gameService;
    
    @PostMapping("/play")
    public ResponseEntity<?> playGame(@RequestBody GameRequest game){
        try{
            gameService.playGame(game);
        }catch(Exception e ) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST); 
        }
        return  new ResponseEntity<String>(HttpStatus.OK); 
    }

}
