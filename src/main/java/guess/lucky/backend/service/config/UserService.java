//package guess.lucky.backend.service.config;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import guess.lucky.backend.models.config.MyUserDetails;
//import guess.lucky.backend.models.config.User;
//import guess.lucky.backend.repository.config.UserRepository;
//
////@Service
//public class UserService implements UserDetailsService {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> user = userRepository.findByUsername(username);
//        user.orElseThrow( () -> new UsernameNotFoundException("Not found: " + username));
//        return user.map(MyUserDetails::new).get();
//    }
//}
