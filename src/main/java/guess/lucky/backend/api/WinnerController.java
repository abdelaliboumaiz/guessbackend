package guess.lucky.backend.api;


import guess.lucky.backend.exceptions.ServiceException;
import guess.lucky.backend.models.Winners;
import guess.lucky.backend.models.config.User;
import guess.lucky.backend.repository.WinnersRepository;
import guess.lucky.backend.repository.config.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/public")
public class WinnerController {
    private static final Logger log = LoggerFactory.getLogger(publicResources.class);

    @Autowired
    private WinnersRepository winnersRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/winners")
    public ResponseEntity<Winners> createWinners(@RequestBody Winners winners){
        User user = userRepository.findById(winners.getUser().getId()).orElse(null);
        if(user == null){
            throw new ServiceException("User not found");
        }
        winners.setUser(user);
        return new ResponseEntity<>(winnersRepository.save(winners), HttpStatus.OK);
    }

    @GetMapping("/isWinners")
    ResponseEntity<List<Winners>> createWinners(@RequestParam long id){
        User user = userRepository.findById(id).orElseThrow(null);
        if(user != null){
            return new ResponseEntity<>(winnersRepository.findByisCheckAndUser_Id(false, id), HttpStatus.OK);
        }
        throw new ServiceException("User not found");
    }

    @GetMapping("/winners")
    public ResponseEntity<List<Winners>> getWinners(){
        return new ResponseEntity<>(winnersRepository.findAll(), HttpStatus.OK);
    }

}
