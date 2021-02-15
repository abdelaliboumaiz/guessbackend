package guess.lucky.backend.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import guess.lucky.backend.models.config.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SponsorshipDailyConsumption {
    @Id
    @Column(name ="id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private User concernedUser; 
    @Temporal(TemporalType.DATE)
    private Date requestDate; 
    @Column(columnDefinition = "integer default 0")
    private Integer usedTries; 

}
