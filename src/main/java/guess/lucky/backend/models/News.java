package guess.lucky.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class News {
    @Id
    @Column(name ="id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Temporal(TemporalType.DATE)
    private Date expiredDate;
    private String title;
    private String subtitle;
    private String imageUrl;
    private String content;

}
