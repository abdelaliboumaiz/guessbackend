package guess.lucky.backend.api;

import guess.lucky.backend.service.mail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class publicResources {

    @Autowired
    MailService mailService;

    @GetMapping("/public")
    public String sayPublic(){

        mailService.sendMail("smexabdel@gmail.com", "Support", "Hello World");
        return "Hello world";
    }
}
