package guess.lucky.backend.models.config;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TB_USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String username;
    private String password;
    private boolean active;
    private String roles;
    private String telephone;
    private int age;
    private boolean isValidNumber;
    private Date registration_date;
    private int score;
    private int heart_win;
    private int heart_peer_day;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isValidNumber() {
        return isValidNumber;
    }

    public void setValidNumber(boolean validNumber) {
        isValidNumber = validNumber;
    }

    public Date getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(Date registration_date) {
        this.registration_date = registration_date;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getHeart_win() {
        return heart_win;
    }

    public void setHeart_win(int heart_win) {
        this.heart_win = heart_win;
    }

    public int getHeart_peer_day() {
        return heart_peer_day;
    }

    public void setHeart_peer_day(int heart_peer_day) {
        this.heart_peer_day = heart_peer_day;
    }
}
