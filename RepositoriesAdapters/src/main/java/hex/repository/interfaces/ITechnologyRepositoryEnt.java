package hex.repository.interfaces;

import hex.model.technologies.TechnologyEnt;

import java.util.List;
import java.util.Optional;

public interface ITechnologyRepositoryEnt {
    List<TechnologyEnt> selectTechnologyByName(String technologyName);
    List<TechnologyEnt> getAllTechnologies();
    List<TechnologyEnt>  getAllTechnologiesFrontEnd();
    List<TechnologyEnt> getAllTechnologiesBackEnd();
}
