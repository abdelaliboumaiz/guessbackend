package guess.lucky.backend.models.config;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_USER")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String username;
    private String password;
    private boolean active;
    private String roles;
    private String mail;
    private int age;
    private boolean isValidMail;
    private int confirmationCode;
    private Date registrationDate;
    private int score;
    private int heartWin;
    private int heartPeerDay;
    private String token;
    private String firstname;
    private String lastname;
    private String sponsoredId;
    private String city;
    private String number;
    private String address;

}
