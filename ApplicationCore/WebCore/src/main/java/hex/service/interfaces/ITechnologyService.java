package hex.service.interfaces;

import hex.model.technologies.Technology;

import java.util.List;
import java.util.Optional;

public interface ITechnologyService {
    List<Technology> getAllTechnologies();
    Optional<Technology> selectTechnologyByName(String technologyName);
    List<Technology> getAllTechnologiesBackEnd();
    public List<Technology>  getAllTechnologiesFrontEnd();
}
