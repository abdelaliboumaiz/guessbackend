package guess.lucky.backend;

import guess.lucky.backend.models.config.User;
import guess.lucky.backend.repository.config.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@SpringBootApplication
@EnableJpaRepositories
@EnableSwagger2
@EnableScheduling
public class GuessluckyApplication  extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(GuessluckyApplication.class, args);
	}

	/*@Autowired
	UserRepository userRepository;
	@Component
	public class MyRunner implements CommandLineRunner {
		@Override
		public void run(String... args) throws Exception {
			User user = new User();
			user.setUsername("admin");
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			user.setActive(true);
			user.setRoles("ROLE_ADMIN");
			userRepository.save(user);
		}
	}*/

}
