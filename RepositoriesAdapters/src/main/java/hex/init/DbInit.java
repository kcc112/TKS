package hex.init;


import hex.model.developers.BackendEnt;
import hex.model.developers.FrontEndEnt;
import hex.model.technologies.NodeJsEnt;
import hex.model.technologies.ReactEnt;
import hex.model.technologies.RubyOnRailsEnt;
import hex.model.technologies.TechnologyEnt;
import hex.model.users.AdminEnt;
import hex.model.users.ClientEnt;
import hex.model.users.ResourceAdministratorEnt;
import hex.repository.DeveloperRepositoryEnt;
import hex.repository.UserRepositoryEnt;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DbInit implements CommandLineRunner {
    private UserRepositoryEnt userRepository;
    private DeveloperRepositoryEnt developerRepositoryEnt;
    private PasswordEncoder passwordEncoder;

    public DbInit(UserRepositoryEnt userRepository, DeveloperRepositoryEnt developerRepositoryEnt, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.developerRepositoryEnt = developerRepositoryEnt;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        userRepository.addUser(new AdminEnt("a@a.com","Kamil", "Celejewski","ADMIN", UUID.randomUUID(), passwordEncoder.encode("123")));
        userRepository.addUser(new ClientEnt("b@b.com","Marcin1", "Morawski","CLIENT", UUID.randomUUID(),passwordEncoder.encode("123")));
        userRepository.addUser(new ClientEnt("c@c.com","Marcin2", "Morawski","CLIENT", UUID.randomUUID(),passwordEncoder.encode("123")));
        userRepository.addUser(new ClientEnt("d@d.com","Marcin3", "Morawski","CLIENT", UUID.randomUUID(),passwordEncoder.encode("123")));
        userRepository.addUser(new ClientEnt("e@e.com","Marcin4", "Morawski","CLIENT", UUID.randomUUID(),passwordEncoder.encode("123")));
        userRepository.addUser(new ResourceAdministratorEnt("f@f.com","Szymon", "Dobrowolski","RESOURCE_ADMINISTRATOR", UUID.randomUUID(),passwordEncoder.encode("123")));

        TechnologyEnt rubyOnRails = new RubyOnRailsEnt();
        TechnologyEnt react = new ReactEnt();
        TechnologyEnt nodeJs = new NodeJsEnt();
        developerRepositoryEnt.addDeveloper(new BackendEnt("Ernest","Kowalski", rubyOnRails, UUID.randomUUID()));
        developerRepositoryEnt.addDeveloper(new FrontEndEnt("Wiktor","Kowalski", react, UUID.randomUUID()));
        developerRepositoryEnt.addDeveloper(new BackendEnt("Bartek","Kowalski", nodeJs, UUID.randomUUID()));
    }
}
