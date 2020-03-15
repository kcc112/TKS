package hex.repository.interfaces;

import hex.model.developers.DeveloperEnt;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IDeveloperRepositoryEnt {
    void addDeveloper(UUID id, DeveloperEnt developer);
    void destroyDeveloper(UUID id);
    void updateDeveloper(DeveloperEnt developer);
    Optional<DeveloperEnt> selectDeveloperById(UUID id);
    List<DeveloperEnt> getAllDevelopers();
    List<DeveloperEnt> getAllUnemployedDevelopers();
    List<DeveloperEnt> getDevelopersByName(String name);

    default void addDeveloper(DeveloperEnt developer) {
        UUID id = UUID.randomUUID();
        addDeveloper(id, developer);
    }
}
