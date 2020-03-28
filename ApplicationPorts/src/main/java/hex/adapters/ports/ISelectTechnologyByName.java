package hex.adapters.ports;

import hex.model.technologies.Technology;

import java.util.Optional;

public interface ISelectTechnologyByName {
    Optional<Technology> selectTechnologyByName(String technologyName);
}