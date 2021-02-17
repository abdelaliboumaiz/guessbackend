package guess.lucky.backend.service;

import guess.lucky.backend.exceptions.ServiceException;
import guess.lucky.backend.models.config.User;
import guess.lucky.backend.repository.config.UserRepository;
import guess.lucky.backend.service.mail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MailService mailService;

    public User createUser(User user){
        if(user.getId() != 0 && userRepository.existsById(user.getId())){
            throw new ServiceException();
        }
        if(userRepository.findByMail(user.getMail()) != null){
            throw new ServiceException();
        }
        user.setRoles("ROLE_USER");
        user.setConfirmationCode(mailService.generateCodeMail());
        mailService.sendMail(user.getMail(), "Confirmation mail", "Code confirmation : " + user.getConfirmationCode());
        return userRepository.save(user);
    }

    public User validationMail(long id, int confirmationCode){
        User user = userRepository.findById(id).orElseThrow(()->{return new ServiceException();});
        if(user.getConfirmationCode() == confirmationCode){
            user.setActive(true);
            user.setValidMail(true);
            return userRepository.save(user);
        }else{
            throw new ServiceException();
        }
    }
}
