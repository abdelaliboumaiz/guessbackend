//package guess.lucky.backend.configuration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//
////@EnableWebSecurity
////@Configuration
//public class Security extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http.authorizeRequests()
////                .antMatchers("/*").permitAll();
//////                .antMatchers("/api/admin/**").hasRole("ADMIN")
//////                .antMatchers("/api/users/**").hasAnyRole("ADMIN", "USER")
//////                .and().formLogin();
////        super.configure(http);
////    }
//
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(userDetailsService)
////                .passwordEncoder(getPasswordEncoder());
////    }
//
////    @Bean
////    protected PasswordEncoder getPasswordEncoder(){
////        return new BCryptPasswordEncoder();
////    }
//}
