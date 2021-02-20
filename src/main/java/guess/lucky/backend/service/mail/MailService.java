package guess.lucky.backend.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class MailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendMail(
            String to, int verificationCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@gmail.com");
        message.setTo(to);
        message.setSubject("Validation email");
        String text = "Votre code de validation est : " + verificationCode;
        message.setText(text);
        emailSender.send(message);
    }

    public void resetPassword(String to, int verificationCode){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@gmail.com");
        message.setTo(to);
        message.setSubject("Récuperer votre mot de passe");
        String text = "Votre code de confirmation est : " + verificationCode;
        message.setText(text);
        emailSender.send(message);
    }

    public int generateCodeMail(){
        SecureRandom rnd = new SecureRandom();
        return 100000 + rnd.nextInt(900000);
    }

}
