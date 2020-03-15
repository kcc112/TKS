package hex.service.interfaces;

import hex.model.developers.Developer;
import hex.model.technologies.Technology;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IDeveloperService {
    List<Developer> getAllDevelopers();
    Optional<Developer> selectDeveloperById(UUID id);
    List<Developer> getAllUnemployedDevelopers();
    void addDeveloper(Developer developer, Technology technology);
    void updateDeveloper(Developer developer);
    void destroyDeveloper(UUID id);
    List<Developer> getDevelopersByName(String name);
}
