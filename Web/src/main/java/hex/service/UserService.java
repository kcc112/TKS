package hex.service;

import hex.model.users.Admin;
import hex.model.users.Client;
import hex.model.users.ResourceAdministrator;
import hex.model.users.User;
import hex.repository.interfaces.IUserRepository;
import hex.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void addUser(User user) {
        String userName = user.getUserName();
        String userSurname = user.getUserSurname();
        String userType = user.getUserType();
        String userEmail = user.getUserEmail();
        String password = passwordEncoder.encode(user.getPassword());
        boolean isActive = user.getIsActive();
        UUID id = user.getUserId();

        switch (user.getUserType()) {
            case "ADMIN":
                user = new Admin(userEmail, userName, userSurname, userType, id, password);
                if (!isActive) user.setActive(false);
                userRepository.addUser(user);
                break;
            case "CLIENT":
                user = new Client(userEmail,userName, userSurname, userType, id, password);
                if (!isActive) user.setActive(false);
                userRepository.addUser(user);
                break;
            case "RESOURCE_ADMINISTRATOR":
                user = new ResourceAdministrator(userEmail, userName, userSurname, userType, id, password);
                if (!isActive) user.setActive(false);
                userRepository.addUser(user);
                break;
            default:
                break;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public void destroyUser(UUID id) {
        userRepository.destroyUser(id);
    }

    @Override
    public void updateUser(User user) {
        Optional<User> currentUser = userRepository.selectUserById(user.getUserId());

        if (currentUser.isPresent()) {

            if(!user.getUserName().isBlank()) {
                String name = user.getUserName();
                currentUser.get().setUserName(name);
            }

            if(!user.getUserSurname().isBlank()) {
                String surname = user.getUserSurname();
                currentUser.get().setUserSurname(surname);
            }
            userRepository.updateUser(currentUser.get());
        }
    }

    @Override
    public List<User> getAllActiveClients() {
        return userRepository.getAllActiveClients();
    }

    @Override
    public Optional<User> selectUserById(UUID id) {
        return userRepository.selectUserById(id);
    }

    @Override
    public void activateOrDeactivateUser(UUID id) {
        Optional<User> userToChange = userRepository.selectUserById(id);
        userToChange.ifPresent(user -> {
            if (user.getIsActive()) {
                user.setActive(false);
            } else {
                user.setActive(true);
            }
        });
    }

    @Override
    public List<User> getUsersByName(String name) {
        return userRepository.getUsersByName(name);
    }

    @Override
    public Optional<User> selectUserByEmail(String email) {
        return userRepository.selectUserByEmail(email);
    }
}
