package guess.lucky.backend.api.privateApi;

import guess.lucky.backend.models.config.User;
import guess.lucky.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/user-id")
    public ResponseEntity<User> getUserById(@RequestParam long id){
        log.info("User get " + id);
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

}
