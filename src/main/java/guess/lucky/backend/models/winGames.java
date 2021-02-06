package guess.lucky.backend.models;

import java.util.Date;

public class winGames {
    private long id;
    private int[] numbers;
    private int[] horoscopes;
    private Date win_date;
    private int amount_won; // montant gagné 1000dh

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Date getWin_date() {
        return win_date;
    }

    public void setWin_date(Date win_date) {
        this.win_date = win_date;
    }

    public int getAmount_won() {
        return amount_won;
    }

    public void setAmount_won(int amount_won) {
        this.amount_won = amount_won;
    }
}
