package hex.repository;

import hex.model.developers.Developer;
import hex.repository.interfaces.IDeveloperRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class DeveloperRepository implements IDeveloperRepository {

    private List<Developer> developers = new ArrayList<>();

    @Override
    public void addDeveloper(UUID id, Developer developer) {
        developer.setDeveloperId(id);
        synchronized (this) {
            developers.add(developer);
        }
    }

    @Override
    public void destroyDeveloper(UUID id) {
        Optional<Developer> developerToDelete = selectDeveloperById(id);
        synchronized (this) {
            developerToDelete.ifPresent(developer -> developers.remove(developer));
        }
    }

    @Override
    public Optional<Developer> selectDeveloperById(UUID id) {
        return developers.stream()
                .filter(user -> user.getDeveloperId().equals(id))
                .findFirst();
    }

    @Override
    public List<Developer> getAllDevelopers() {
        return developers;
    }

    @Override
    public void updateDeveloper(Developer developer) {
        Optional<Developer> userCurrent = selectDeveloperById(developer.getDeveloperId());
        if (userCurrent.isPresent()) {
            int indexOfPersonToUpdate = developers.indexOf(userCurrent.get());
            synchronized (this) {
                developers.set(indexOfPersonToUpdate, developer);
            }
        }
    }

    @Override
    public List<Developer> getDevelopersByName(String name) {
        List<Developer> developersList = new ArrayList<>();
        for (Developer developer : developers) {
            if (developer.getDeveloperName().equals(name)) {
                developersList.add(developer);
            }
        }
        return developersList;
    }

    @Override
    public List<Developer> getAllUnemployedDevelopers() {
        List<Developer> unemployedDevelopers = new ArrayList<>();
        for (Developer developer : developers) {
            if (!developer.isHired()) {
                unemployedDevelopers.add(developer);
            }
        }
        return unemployedDevelopers;
    }
}
