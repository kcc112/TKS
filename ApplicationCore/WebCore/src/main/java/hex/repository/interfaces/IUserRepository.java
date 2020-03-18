package hex.repository.interfaces;

import hex.model.users.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserRepository {

    void addUser(UUID id, User user);
    void destroyUser(UUID id);
    void updateUser(User user);
    Optional<User> selectUserById(UUID id);
    List<User> getAllUsers();
    List<User> getAllActiveClients();
     List<User> getUsersByName(String name);
    Optional<User> getUserByName(String name);
    Optional<User> selectUserByEmail(String email);

    default void addUser(User user) {
        UUID id = UUID.randomUUID();
        addUser(id, user);
    }
}
