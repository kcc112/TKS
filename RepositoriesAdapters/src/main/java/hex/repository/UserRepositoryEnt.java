package hex.repository;

import hex.model.users.ClientEnt;
import hex.model.users.UserEnt;
import hex.repository.interfaces.IUserRepositoryEnt;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryEnt implements IUserRepositoryEnt {

    private List<UserEnt> users = new ArrayList<>();

    @Override
    public void addUser(UUID id, UserEnt user) {
        user.setUserId(id);
        synchronized (this) {
            users.add(user);
        }
    }

    @Override
    public List<UserEnt> getAllUsers() {
        return users;
    }

    @Override
    public Optional<UserEnt> selectUserById(UUID id) {
        return users.stream()
                .filter(user -> user.getUserId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<UserEnt> selectUserByEmail(String email) {
        return users.stream()
                .filter(user -> user.getUserEmail().equals(email))
                .findFirst();
    }

    @Override
    public void destroyUser(UUID id) {
        Optional<UserEnt> userToDelete = selectUserById(id);
        synchronized (this) {
            userToDelete.ifPresent(user -> users.remove(user));
        }
    }

    @Override
    public void updateUser(UserEnt user) {
        Optional<UserEnt> userCurrent = selectUserById(user.getUserId());
        if (userCurrent.isPresent()) {
            int indexOfPersonToUpdate = users.indexOf(userCurrent.get());
            synchronized (this) {
                users.set(indexOfPersonToUpdate, user);
            }
        }
    }

    @Override
    public List<UserEnt> getAllActiveClients() {
        List<UserEnt> activeClients = new ArrayList<>();
        List<UserEnt> clients = getAllClients();
        for (UserEnt client : clients) {
            if (client.getIsActive()) {
                activeClients.add(client);
            }
        }
        return activeClients;
    }

    @Override
    public List<UserEnt> getUsersByName(String name) {
        List<UserEnt> clients = new ArrayList<>();
        for (UserEnt user : users) {
            if (user.getUserName().equals(name)) {
                clients.add(user);
            }
        }
        return clients;
    }

    @Override
    public Optional<UserEnt> getUserByName(String name) {
        return users.stream()
                .filter(user -> user.getUserName().equals(name))
                .findFirst();
    }

    private List<UserEnt> getAllClients() {
        List<UserEnt> clients = new ArrayList<>();
        for (UserEnt user : users) {
            if (user.getClass().equals(ClientEnt.class)) {
                clients.add(user);
            }
        }
        return clients;
    }
}
