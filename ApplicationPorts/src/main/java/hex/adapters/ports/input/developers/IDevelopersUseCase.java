package hex.adapters.ports.input.developers;

import hex.model.developers.Developer;
import hex.model.technologies.Technology;

import java.util.List;
import java.util.UUID;

public interface IDevelopersUseCase {
    List<Developer> getAllDevelopers();

    void addDeveloper(Developer developer, Technology technology);

    void deleteDeveloper(UUID id);

    List<Developer> getDeveloperByName(String name);

    Developer getDeveloperById(UUID id);
}
