package hex.repository.interfaces;


import hex.model.users.UserEnt;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserRepositoryEnt {

    void addUser(UUID id, UserEnt user);
    void destroyUser(UUID id);
    void updateUser(UserEnt user);
    Optional<UserEnt> selectUserById(UUID id);
    List<UserEnt> getAllUsers();
    List<UserEnt> getAllActiveClients();
    List<UserEnt> getUsersByName(String name);
    Optional<UserEnt> getUserByName(String name);
    Optional<UserEnt> selectUserByEmail(String email);

    default void addUser(UserEnt user) {
        UUID id = UUID.randomUUID();
        addUser(id, user);
    }
}
