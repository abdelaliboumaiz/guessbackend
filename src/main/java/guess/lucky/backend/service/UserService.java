package guess.lucky.backend.service;

import guess.lucky.backend.exceptions.ServiceException;
import guess.lucky.backend.models.config.MyUserDetails;
import guess.lucky.backend.models.config.User;
import guess.lucky.backend.repository.config.UserRepository;
import guess.lucky.backend.service.mail.MailService;
import guess.lucky.backend.variable.StaticVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MailService mailService;

    public User checkUserAndPass(String email, String password){
        User user = userRepository.findByMail(email).orElseThrow(()->{return new ServiceException("Votre email est incorrect");});
        if(!user.getPassword().equals(new BCryptPasswordEncoder().encode(password)))
            throw new ServiceException("Votre mot de passe est incorrect");
        return user;
    }

    public User getUserByEmail(String email){
        return userRepository.findByMail(email).orElseThrow(()->{return new ServiceException("utilisateur non trouver");});
    }

    public User getUserById(long id){
        return userRepository.findById(id).orElseThrow(()->{return new ServiceException("utilisateur non trouver");});
    }

    public User createUser(User user, long id){
        if(user.getId() != 0 && userRepository.existsById(user.getId())){
            throw new ServiceException("Utilisateur existe déja");
        }
        if(userRepository.findByMail(user.getMail()).isPresent()){
            throw new ServiceException("Email existe déja");
        }
        if(id != 0){
            if(!checkIsSponsorship(id)){
                throw new ServiceException("code de parrainage incorrect");
            }
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRoles("ROLE_USER");
        user.setRegistration_date(new Date());
        user.setHeart_peer_day(5);
        user.setValidMail(false);
        user.setScore(0);
        user.setConfirmationCode(mailService.generateCodeMail());
        mailService.sendMail(user.getMail(), user.getConfirmationCode());
        return userRepository.save(user);
    }

    public User validationMail(long id, int confirmationCode){
        User user = userRepository.findById(id).orElseThrow(()->{return new ServiceException("utilisateur non trouver");});
        if(user.getConfirmationCode() == confirmationCode){
            user.setActive(true);
            user.setValidMail(true);
            userRepository.save(user);
            return user;
        }else{
            throw new ServiceException("code de confirmation incorrect");
        }
    }

    //Send confirmation code to reset password
    public String resetPassword(String email){
        User user = userRepository.findByMail(email).orElseThrow(()->{return new ServiceException("email incorrect");});
        user.setConfirmationCode(mailService.generateCodeMail());
        userRepository.save(user);
        try {
            mailService.resetPassword(email, user.getConfirmationCode());
            return "Vérifier votre boite mail";
        }catch (Exception e){
            throw new ServiceException("Mail non envoyé");
        }
    }

    //check confirmation code to reset password
    public User validationPassword(String mail, int confirmationCode){
        User user = userRepository.findByMail(mail).orElseThrow(()->{return new ServiceException("utilisateur non trouver");});
        if(user.getConfirmationCode() == confirmationCode){
            return userRepository.save(user);
        }else{
            throw new ServiceException("code de confirmation incorrect");
        }
    }

    //Update password
    public User updatePassword(User user){
        userRepository.findByMail(user.getMail()).orElseThrow(()->{return new ServiceException("utilisateur non trouver");});
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    //Update User
    public User updateUser(User user){
        userRepository.findByMail(user.getMail()).orElseThrow(()->{return new ServiceException("utilisateur non trouver");});
        return userRepository.save(user);
    }

    // ID is 888888 + ID
    public boolean checkIsSponsorship(long id){
        id = id - StaticVariable.KEY;
        User user = userRepository.findById(id).get();
        if(user != null){
            user.setHeart_win(user.getHeart_win() + 5);
            userRepository.save(user);
            return true;
        }else
            return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        user.orElseThrow( () -> new UsernameNotFoundException("Not found: " + username));
        return user.map(MyUserDetails::new).get();
    }
}
