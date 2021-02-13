package guess.lucky.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import guess.lucky.backend.models.config.User;
import guess.lucky.backend.repository.config.UserRepository;

@SpringBootApplication
@EnableJpaRepositories
public class GuessluckyApplication  extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(GuessluckyApplication.class, args);
	}

	@Component
	public class MyRunner implements CommandLineRunner {

		private Logger logger = LoggerFactory.getLogger(MyRunner.class);

		@Autowired
		private UserRepository repository;

		@Override
		public void run(String... args) throws Exception {
			User user = new User();
			user.setUsername("user");
			user.setPassword(new BCryptPasswordEncoder().encode("user"));
			user.setActive(true);
			user.setRoles("USER");
			repository.save(user);
			logger.info("User created..");

		}
	}

}
