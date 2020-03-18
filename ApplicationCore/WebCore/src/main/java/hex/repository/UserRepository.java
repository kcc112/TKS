package hex.repository;

import hex.model.users.Client;
import hex.model.users.User;
import hex.repository.interfaces.IUserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepository implements IUserRepository {

    private List<User> users = new ArrayList<>();

    @Override
    public void addUser(UUID id, User user) {
        user.setUserId(id);
        synchronized (this) {
            users.add(user);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public Optional<User> selectUserById(UUID id) {
        return users.stream()
                .filter(user -> user.getUserId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<User> selectUserByEmail(String email) {
        return users.stream()
                .filter(user -> user.getUserEmail().equals(email))
                .findFirst();
    }

    @Override
    public void destroyUser(UUID id) {
        Optional<User> userToDelete = selectUserById(id);
        synchronized (this) {
            userToDelete.ifPresent(user -> users.remove(user));
        }
    }

    @Override
    public void updateUser(User user) {
        Optional<User> userCurrent = selectUserById(user.getUserId());
        if (userCurrent.isPresent()) {
            int indexOfPersonToUpdate = users.indexOf(userCurrent.get());
            synchronized (this) {
                users.set(indexOfPersonToUpdate, user);
            }
        }
    }

    @Override
    public List<User> getAllActiveClients() {
        List<User> activeClients = new ArrayList<>();
        List<User> clients = getAllClients();
        for (User client : clients) {
            if (client.getIsActive()) {
                activeClients.add(client);
            }
        }
        return activeClients;
    }

    @Override
    public List<User> getUsersByName(String name) {
        List<User> clients = new ArrayList<>();
        for (User user : users) {
            if (user.getUserName().equals(name)) {
                clients.add(user);
            }
        }
        return clients;
    }

    @Override
    public Optional<User> getUserByName(String name) {
        return users.stream()
                .filter(user -> user.getUserName().equals(name))
                .findFirst();
    }

    private List<User> getAllClients() {
        List<User> clients = new ArrayList<>();
        for (User user : users) {
            if (user.getClass().equals(Client.class)) {
                clients.add(user);
            }
        }
        return clients;
    }
}
