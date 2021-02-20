package guess.lucky.backend.api;

import guess.lucky.backend.exceptions.ServiceException;
import guess.lucky.backend.models.config.User;
import guess.lucky.backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class publicResources {

    private static final Logger log = LoggerFactory.getLogger(publicResources.class);

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ResponseEntity<User> login(@RequestParam String email, @RequestParam String password){
        return new ResponseEntity<>(userService.checkUserAndPass(email, password), HttpStatus.OK);
    }

    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestBody User user, @RequestParam(required = false) Long id){
        log.info("User created " + user.getMail());
        if(id == null){
            return new ResponseEntity<>(userService.createUser(user, 0), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(userService.createUser(user, id), HttpStatus.OK);
        }
    }

    @GetMapping("/user-email")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email){
        log.info("User get " + email);
        return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/user-id")
    public ResponseEntity<User> getUserById(@RequestParam long id){
        log.info("User get " + id);
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping("/validation-mail")
    public ResponseEntity<User> validationEmail(@RequestParam long id,@RequestParam int confirmationCode){
        log.info("mail verified " + confirmationCode);
        return new ResponseEntity<>(userService.validationMail(id, confirmationCode), HttpStatus.OK);
    }

    @GetMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String email){
        log.info("reset password for " + email);
        return new ResponseEntity<>(userService.resetPassword(email), HttpStatus.OK);
    }

    @GetMapping("/reset-password-validation")
    public ResponseEntity<User> resetPasswordValidation(@RequestParam String email,@RequestParam  int confirmationCode){
        log.info("reset password confirmation " + confirmationCode);
        return new ResponseEntity<>(userService.validationPassword(email, confirmationCode), HttpStatus.OK);
    }

    @PostMapping("/update-password")
    public ResponseEntity<User> updatePassword(@RequestBody User user){
        log.info("update password for " + user.getMail());
        return new ResponseEntity<>(userService.updatePassword(user), HttpStatus.OK);
    }

}
