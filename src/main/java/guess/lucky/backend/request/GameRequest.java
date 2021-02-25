package guess.lucky.backend.request;

import lombok.Data;

@Data
public class GameRequest {
    Long user_id; 
    String chosenNumbers;
    String chosenHoroscopes;
}
