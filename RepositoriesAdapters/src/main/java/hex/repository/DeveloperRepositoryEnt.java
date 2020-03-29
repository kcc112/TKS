package hex.repository;

import hex.model.developers.BackendEnt;
import hex.model.developers.DeveloperEnt;
import hex.model.developers.FrontEndEnt;
import hex.model.technologies.NodeJsEnt;
import hex.model.technologies.ReactEnt;
import hex.model.technologies.RubyOnRailsEnt;
import hex.model.technologies.TechnologyEnt;
import hex.repository.interfaces.IDeveloperRepositoryEnt;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class DeveloperRepositoryEnt implements IDeveloperRepositoryEnt {

    private List<DeveloperEnt> developers = new ArrayList<>();

    public DeveloperRepositoryEnt() {
        TechnologyEnt rubyOnRails = new RubyOnRailsEnt();
        TechnologyEnt react = new ReactEnt();
        TechnologyEnt nodeJs = new NodeJsEnt();
        developers.add(new BackendEnt("Ernest","Kowalski", rubyOnRails, UUID.randomUUID()));
        developers.add(new FrontEndEnt("Wiktor","Kowalski", react, UUID.randomUUID()));
        developers.add(new BackendEnt("Bartek","Kowalski", nodeJs, UUID.randomUUID()));
    }

    @Override
    public void addDeveloper(UUID id, DeveloperEnt developer) {
        developer.setDeveloperId(id);
        synchronized (this) {
            developers.add(developer);
        }
    }

    @Override
    public void destroyDeveloper(UUID id) {
        Optional<DeveloperEnt> developerToDelete = selectDeveloperById(id);
        synchronized (this) {
            developerToDelete.ifPresent(developer -> developers.remove(developer));
        }
    }

    @Override
    public Optional<DeveloperEnt> selectDeveloperById(UUID id) {
        return developers.stream()
                .filter(user -> user.getDeveloperId().equals(id))
                .findFirst();
    }

    @Override
    public List<DeveloperEnt> getAllDevelopers() {
        return developers;
    }

    @Override
    public void updateDeveloper(DeveloperEnt developer) {
        Optional<DeveloperEnt> userCurrent = selectDeveloperById(developer.getDeveloperId());
        if (userCurrent.isPresent()) {
            int indexOfPersonToUpdate = developers.indexOf(userCurrent.get());
            synchronized (this) {
                developers.set(indexOfPersonToUpdate, developer);
            }
        }
    }

    @Override
    public List<DeveloperEnt> getDevelopersByName(String name) {
        List<DeveloperEnt> developersList = new ArrayList<>();
        for (DeveloperEnt developer : developers) {
            if (developer.getDeveloperName().equals(name)) {
                developersList.add(developer);
            }
        }
        return developersList;
    }

    @Override
    public List<DeveloperEnt> getAllUnemployedDevelopers() {
        List<DeveloperEnt> unemployedDevelopers = new ArrayList<>();
        for (DeveloperEnt developer : developers) {
            if (!developer.isHired()) {
                unemployedDevelopers.add(developer);
            }
        }
        return unemployedDevelopers;
    }
}
