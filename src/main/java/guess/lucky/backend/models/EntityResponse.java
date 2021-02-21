package guess.lucky.backend.models;

import java.io.Serializable;


public class EntityResponse implements Serializable {
    private String message;

    public EntityResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
