package hex.init;

import hex.model.developers.Backend;
import hex.model.developers.FrontEnd;
import hex.model.technologies.NodeJs;
import hex.model.technologies.React;
import hex.model.technologies.RubyOnRails;
import hex.model.technologies.Technology;
import hex.model.users.Admin;
import hex.model.users.Client;
import hex.model.users.ResourceAdministrator;
import hex.repository.DeveloperRepository;
import hex.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DbInit implements CommandLineRunner {
    private UserRepository userRepository;
    private DeveloperRepository developerRepository;
    private PasswordEncoder passwordEncoder;

    public DbInit(UserRepository userRepository, DeveloperRepository developerRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.developerRepository = developerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        userRepository.addUser(new Admin("a@a.com","Kamil", "Celejewski","ADMIN", UUID.randomUUID(), passwordEncoder.encode("123")));
        userRepository.addUser(new Client("b@b.com","Marcin1", "Morawski","CLIENT", UUID.randomUUID(),passwordEncoder.encode("123")));
        userRepository.addUser(new Client("c@c.com","Marcin2", "Morawski","CLIENT", UUID.randomUUID(),passwordEncoder.encode("123")));
        userRepository.addUser(new Client("d@d.com","Marcin3", "Morawski","CLIENT", UUID.randomUUID(),passwordEncoder.encode("123")));
        userRepository.addUser(new Client("e@e.com","Marcin4", "Morawski","CLIENT", UUID.randomUUID(),passwordEncoder.encode("123")));
        userRepository.addUser(new ResourceAdministrator("f@f.com","Szymon", "Dobrowolski","RESOURCE_ADMINISTRATOR", UUID.randomUUID(),passwordEncoder.encode("123")));

        Technology rubyOnRails = new RubyOnRails();
        Technology react = new React();
        Technology nodeJs = new NodeJs();
        developerRepository.addDeveloper(new Backend("Ernest","Kowalski", rubyOnRails, UUID.randomUUID()));
        developerRepository.addDeveloper(new FrontEnd("Wiktor","Kowalski", react, UUID.randomUUID()));
        developerRepository.addDeveloper(new Backend("Bartek","Kowalski", nodeJs, UUID.randomUUID()));
    }
}
