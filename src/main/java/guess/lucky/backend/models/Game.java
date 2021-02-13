package guess.lucky.backend.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import guess.lucky.backend.models.config.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Game {
    @Id
    @Column(name ="id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private User user;
    private Date gamePlayedAt;
    private String chosenNumbers; //6
    private String horoscopes; //2
    
    
    public void setChosenNumbers(List<Integer> numbers) {
        String chosenNumbers = new String(); 
        for(Integer n : numbers) {
            String numberStringFormat = Integer.toString(n); 
            if(numbers.indexOf(n)==numbers.size()-1) {
                chosenNumbers.concat(numberStringFormat);
            }
            chosenNumbers.concat(numberStringFormat+","); 
        }
        this.chosenNumbers=chosenNumbers;
    }
    public void setHoroscopes(List<String> horoscopes) {
        String chosenHoroscopes = new String(); 
        for(String s : horoscopes) {
            if(horoscopes.indexOf(s)==horoscopes.size()-1) {
                chosenHoroscopes.concat(s);
            }
            chosenHoroscopes.concat(s+","); 
        }
        this.horoscopes=chosenHoroscopes; 
    }
    public List<Integer> getChosenNumbers(){
        return null;
    }
}
