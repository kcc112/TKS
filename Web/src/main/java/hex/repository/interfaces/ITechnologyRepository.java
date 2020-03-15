package hex.repository.interfaces;

import hex.model.technologies.Technology;

import java.util.List;
import java.util.Optional;

public interface ITechnologyRepository {
    Optional<Technology> selectTechnologyByName(String technologyName);
    List<Technology> getAllTechnologies();
    List<Technology>  getAllTechnologiesFrontEnd();
    List<Technology> getAllTechnologiesBackEnd();
}
