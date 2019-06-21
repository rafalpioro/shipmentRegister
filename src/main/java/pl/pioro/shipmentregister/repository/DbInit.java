package pl.pioro.shipmentregister.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.pioro.shipmentregister.entity.Role;
import pl.pioro.shipmentregister.entity.User;

@Service
public class DbInit implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        //create roles
        Role admin = new Role();
        admin.setName("ROLE_ADMIN");
        Role user = new Role();
        user.setName("ROLE_USER");
        Role viewer = new Role();
        viewer.setName("ROLE_VIEWER");
        roleRepository.save(admin);
        roleRepository.save(user);
        roleRepository.save(viewer);

        //create users
        User rafal = new User();
        rafal.setName("admin");
        rafal.setEmail("admin@gmail.com");
        rafal.setPassword(passwordEncoder.encode("admin"));
        rafal.setRole(admin);
        rafal.setActive(true);

        User magda = new User();
        magda.setName("user");
        magda.setEmail("user@gmail.com");
        magda.setPassword(passwordEncoder.encode("user"));
        magda.setRole(user);
        magda.setActive(true);

        User tomek = new User();
        tomek.setName("viewer");
        tomek.setEmail("viewer@gmail.com");
        tomek.setPassword(passwordEncoder.encode("viewer"));
        tomek.setRole(viewer);
        tomek.setActive(true);

        //add users to db
        this.userRepository.save(rafal);
        this.userRepository.save(magda);
        this.userRepository.save(tomek);
    }
}
