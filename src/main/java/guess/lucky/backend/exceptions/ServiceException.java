package guess.lucky.backend.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ServiceException extends RuntimeException{

    public ServiceException(String errorMessage) {
        super(errorMessage);
    }
}
