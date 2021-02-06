package guess.lucky.backend.models;

import guess.lucky.backend.models.config.User;

import java.util.Date;

public class Game {
    private long id;
    private User user;
    private Date playing_date;
    private int[] numbers; //6
    private int[] horoscopes; //2
    private int score;

    public Game() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getPlaying_date() {
        return playing_date;
    }

    public void setPlaying_date(Date playing_date) {
        this.playing_date = playing_date;
    }

    public int[] getNumbers() {
        return numbers;
    }

    public void setNumbers(int[] numbers) {
        this.numbers = numbers;
    }

    public int[] getHoroscopes() {
        return horoscopes;
    }

    public void setHoroscopes(int[] horoscopes) {
        this.horoscopes = horoscopes;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
