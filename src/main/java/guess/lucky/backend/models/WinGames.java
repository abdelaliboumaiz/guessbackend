package guess.lucky.backend.models;

import guess.lucky.backend.models.config.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WinGames {
    @Id
    @Column(name ="id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String chosenNumbers;
    private String chosenHoroscopes;
    @Temporal(TemporalType.DATE)
    private Date winDate;
    private int amountWon;

}
