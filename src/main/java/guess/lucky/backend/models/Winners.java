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
public class Winners {
    @Id
    @Column(name ="id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private User user;
    private String title;
    private String subtile;
    private String imageUrl;
    private Date winDate;
    private int amount;
    private boolean isCheck;

}
