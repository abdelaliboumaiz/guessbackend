package guess.lucky.backend.models;

import guess.lucky.backend.models.config.User;

public class Winners {
    private long id;
    private User user;
    private String title;
    private String subtile;
    private String image_url;
    private String win_date;

    public Winners() {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtile() {
        return subtile;
    }

    public void setSubtile(String content) {
        this.subtile = content;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getWin_date() {
        return win_date;
    }

    public void setWin_date(String win_date) {
        this.win_date = win_date;
    }
}
