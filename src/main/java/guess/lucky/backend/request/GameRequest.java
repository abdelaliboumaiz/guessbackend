package guess.lucky.backend.request;

import java.util.List;

import lombok.Data;

@Data
public class GameRequest {
    Long user_id; 
    List<Integer> chosenNumber; 
    List<Horoscope> horoscopes; 
    
    @Data
    public class Horoscope { 
        String name; 
    }

}
