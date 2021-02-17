package guess.lucky.backend.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class publicResources {

    @GetMapping("/public")
    public String sayPublic(){
        return "Hello world";
    }
}
