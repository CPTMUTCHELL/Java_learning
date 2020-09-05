package example;

import example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;
import java.security.Principal;
@EnableWebSecurity
@SpringBootApplication()

@EnableJpaRepositories
public class DemoApplication extends WebSecurityConfigurerAdapter  {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

    }
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select login ,pass, 'true' from users where login=?").
                authoritiesByUsernameQuery(
                        "select login,roles.role from roles inner join user_roles on roles.id = " +
                                "user_roles.roles_id\n" +
                        "inner join users on user_roles.user_id = users.id where users.login=?").
                passwordEncoder(passwordEncoder()).rolePrefix("ROLE_");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login","/register").permitAll().

                antMatchers("/users").hasRole("ADMIN")  .anyRequest().authenticated().and().csrf().disable().
                formLogin().loginPage("/login").defaultSuccessUrl("/profile").
                permitAll().and().logout().logoutSuccessUrl("/login").permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
