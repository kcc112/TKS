package hex.service.interfaces;

import hex.model.users.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserService {
    List<User> getAllUsers();
    void addUser(User user);
    void destroyUser(UUID id);
    void updateUser(User user);
    void activateOrDeactivateUser(UUID id);
    List<User> getAllActiveClients();
    Optional<User> selectUserById(UUID id);
    List<User> getUsersByName(String name);
    Optional<User> selectUserByEmail(String email);
}
