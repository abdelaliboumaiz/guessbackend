package guess.lucky.backend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import guess.lucky.backend.models.config.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Sponsorship {
    
    @Id
    @Column(name ="id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id; 
    @OneToOne
    @JoinColumn(name="user_id",referencedColumnName="id")
    private User sponsor;   
    @Column(columnDefinition = "integer default 0")
    private Integer howManyPeopleISponsor;

}
