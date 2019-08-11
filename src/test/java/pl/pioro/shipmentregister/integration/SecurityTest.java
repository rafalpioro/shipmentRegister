package pl.pioro.shipmentregister.integration;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import pl.pioro.shipmentregister.entity.Role;
import pl.pioro.shipmentregister.entity.User;
import pl.pioro.shipmentregister.repository.RoleRepository;
import pl.pioro.shipmentregister.repository.UserRepository;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class SecurityTest {

    public static final String USER_NAME = "aaaa";
    public static final String USER_EMAIL = "a@a.pl";
    public static final String USER_PASSWORD = "aaaa";
    @LocalServerPort
    int serverPort;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    //Sellenium protractgor

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Before
    public void init(){
        port=serverPort;
    }


    @Test
    public void shouldRestAccessForbidden(){

        get("/admin-all/clients").then().statusCode(403);
    }

    @Test
    public void shouldRestAccessOK(){

        createActiveUser();

        String header = given().body(new UserCredentials(USER_EMAIL, USER_PASSWORD))
                .when().post("/login").then().statusCode(200).extract().header("Authorization");

        assertThat(header).startsWith("Bearer");

        given().header("Authorization", header).when().get("/admin-all/clients").then().statusCode(200);
    }


    private class UserCredentials {
        private final String email;
        private final String password;

        public UserCredentials(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }
    }

    private void createActiveUser() {
        User user = new User();
        Role role =  new Role();
        role.setName("ROLE_ADMIN");
        role.setId(1L);
        roleRepository.save(role);
        user.setActive(true);
        user.setName(USER_NAME);
        user.setEmail(USER_EMAIL);
        user.setPassword(passwordEncoder.encode(USER_PASSWORD));
        user.setRole(role);
        userRepository.save(user);
    }
}

