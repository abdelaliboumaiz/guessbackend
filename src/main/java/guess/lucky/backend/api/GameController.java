package guess.lucky.backend.api;

import guess.lucky.backend.exceptions.ServiceException;
import guess.lucky.backend.models.EntityResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import guess.lucky.backend.request.GameRequest;
import guess.lucky.backend.service.GameService;

@RestController
@RequestMapping("/api")
public class GameController {
    private static final Logger log = LoggerFactory.getLogger(GameController.class);
    @Autowired
    private GameService gameService;

    @GetMapping
    public ResponseEntity<EntityResponse> test(){
        log.info("Test secure In");
        return new ResponseEntity<>(new EntityResponse("message"), HttpStatus.OK);
    }
    
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
