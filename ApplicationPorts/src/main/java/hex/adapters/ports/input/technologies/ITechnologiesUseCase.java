package hex.adapters.ports.input.technologies;

import hex.model.technologies.Technology;

import java.util.List;

public interface ITechnologiesUseCase {
    List<Technology> getAll();

    List<Technology> getAllBackend();

    List<Technology> getAllFrontend();

    List<Technology> selectByName(String technologyName);
}