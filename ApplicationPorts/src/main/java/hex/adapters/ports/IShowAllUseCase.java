package hex.adapters.ports;

import hex.model.technologies.Technology;

import java.util.List;
import java.util.Optional;

public interface IShowAllUseCase {
    List<Technology> getAll();

    List<Technology> getAllBackend();

    List<Technology> getAllFrontend();

    Optional<Technology> selectByName(String technologyName);
}